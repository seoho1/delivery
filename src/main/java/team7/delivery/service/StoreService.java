package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team7.delivery.dto.menu.MenuDto;
import team7.delivery.dto.store.StoreMenuResponseDto;
import team7.delivery.dto.store.StoreRequestDto;
import team7.delivery.dto.store.StoreResponseDto;
import team7.delivery.entity.Menu;
import team7.delivery.entity.Owner;
import team7.delivery.entity.Store;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.MenuRepository;
import team7.delivery.repository.OwnerRepository;
import team7.delivery.repository.StoreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * StoreService
 */
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OwnerRepository ownerRepository;

    /**
     * 가게 생성
     *
     * @param requestDto 가게 생성 요청 데이터
     * @param owner      가게 사장 정보
     * @return 생성된 가게의 DTO
     */
    public StoreResponseDto createStore(StoreRequestDto requestDto, Owner owner) {

        // 사장이 DB에 존재하는지 확인
        Optional<Owner> existingOwner = ownerRepository.findByEmail(owner.getEmail());
        if (!existingOwner.isPresent()) {
            // 사장이 DB에 존재하지 않으면 새로 저장
            ownerRepository.save(owner);
        } else {
            owner = existingOwner.get();  // 이미 존재하는 owner 객체 사용
        }

        // 사장의 가게 갯수 확인
        long storeCount = storeRepository.countByOwnerAndIsDeletedFalse(owner);
        if (storeCount >= 3) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.MAX_STORE_LIMIT, ApiException.class);
        }

        // 가게 엔티티 생성 및 저장
        Store store = Store.of(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime(), owner);

        Store savedStore = storeRepository.save(store);
        return StoreResponseDto.of(savedStore);
    }

    /**
     * 가게 이름으로 가게 목록 검색
     *
     * @param storeName 조회할 가게 이름
     * @return 검색된 가게 목록의 DTO 리스트
     */
    public List<StoreResponseDto> getStoresByName(String storeName) {
        // 삭제되지 않은 가게를 이름으로 조회
        List<Store> stores = storeRepository.findByStoreNameContainingAndIsDeletedFalse(storeName);

        // Store 엔티티를 StoreResponseDto로 변환
        return stores.stream()
                .map(StoreResponseDto::of)
                .collect(Collectors.toList());
    }

    /**
     * 가게 ID로 단일 가게 조회
     *
     * @param storeId 조회할 가게 ID
     * @return 가게 정보와 메뉴 목록을 포함한 DTO
     */
    public StoreResponseDto getStoreById(Long storeId) {
        // 가게 ID로 가게 조회
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_NOT_FOUND, ApiException.class));

        // 해당 가게의 메뉴 목록 조회
        List<Menu> menus = menuRepository.findByStoreIdAndIsDeletedFalse(storeId);
        List<MenuDto> menuDtos = menus.stream()
                .map(MenuDto::menuDto)
                .collect(Collectors.toList());

        return StoreMenuResponseDto.of(store, menuDtos);
    }

    /**
     * 가게 정보 수정
     *
     * @param storeId    수정할 가게 ID
     * @param requestDto 수정 요청 데이터
     * @param owner      가게 사장 정보
     * @return 수정된 가게의 DTO
     */
    @Transactional
    public StoreResponseDto updateStore(Long storeId, StoreRequestDto requestDto, Owner owner) {
        // 가게 ID로 가게 조회
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_NOT_FOUND, ApiException.class));

        // 요청한 자가 해당 가게의 사장인지 확인
        if (store.getOwner().equals(owner)) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.FORBIDDEN_UPDATE, ApiException.class);
        }

        // 가게 정보 수정
        store.update(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime());
        return StoreResponseDto.of(store);
    }

    /**
     * 가게 삭제
     *
     * @param storeId 삭제할 가게 ID
     * @param owner   가게 사장 정보
     * @return HTTP 상태 코드 204
     */
    @Transactional
    public ResponseEntity<String> deleteStore(Long storeId, Owner owner) {
        // 가게 ID로 가게 조회
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_NOT_FOUND, ApiException.class));

        // 요청한 자가 해당 가게의 사장인지 확인
        if (!store.getOwner().equals(owner)) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.FORBIDDEN_DELETE, ApiException.class);
        }

        // 가게 삭제 처리
        store.setDeleted(true);
        return ResponseEntity.noContent().build();
    }
}
