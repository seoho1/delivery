package team7.delivery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OwnerRepository ownerRepository;

    public StoreResponseDto createStore(StoreRequestDto requestDto, Owner owner) {

        Optional<Owner> existingOwner = ownerRepository.findByEmail(owner.getEmail());
        if (!existingOwner.isPresent()) {
            // Owner가 DB에 없다면 새로 저장
            ownerRepository.save(owner);
        } else {
            owner = existingOwner.get();  // 이미 존재하는 owner 객체 사용
        }

        long storeCount = storeRepository.countByOwnerAndIsDeletedFalse(owner);
        if (storeCount >= 3) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.MAX_STORE_LIMIT, ApiException.class);
        }

        Store store = Store.of(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime(), owner);

        Store savedStore = storeRepository.save(store);
        return StoreResponseDto.of(savedStore);
    }

    public List<StoreResponseDto> getStoresByName(String storeName) {
        List<Store> stores = storeRepository.findByStoreNameContainingAndIsDeletedFalse(storeName);
        return stores.stream()
                .map(StoreResponseDto::of)
                .collect(Collectors.toList());
    }

    public StoreResponseDto getStoreById(Long storeId) {
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_NOT_FOUND, ApiException.class));
        List<Menu> menus = menuRepository.findByStoreIdAndIsDeletedFalse(storeId);
        List<MenuDto> menuDtos = menus.stream()
                .map(MenuDto::menuDto)
                .collect(Collectors.toList());

        return StoreMenuResponseDto.of(store, menuDtos);
    }

    @Transactional
    public StoreResponseDto updateStore(Long storeId, StoreRequestDto requestDto, Owner owner) {
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_NOT_FOUND, ApiException.class));
        if (!store.getOwner().equals(owner)) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.FORBIDDEN_UPDATE, ApiException.class);
        }

        store.update(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime());
        return StoreResponseDto.of(store);
    }

    @Transactional
    public ResponseEntity<String> deleteStore(Long storeId, Owner owner) {
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.STORE_NOT_FOUND, ApiException.class));
        if (!store.getOwner().equals(owner)) {
            throw ExceptionUtil.throwErrorMessage(ErrorMessage.FORBIDDEN_DELETE, ApiException.class);
        }

        store.setDeleted(true);
        return ResponseEntity.noContent().build();
    }
}
