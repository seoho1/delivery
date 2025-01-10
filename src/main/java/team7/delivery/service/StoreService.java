package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import team7.delivery.repository.MenuRepository;
import team7.delivery.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public StoreResponseDto createStore(StoreRequestDto requestDto, Owner owner) {

        if (!owner.isOwner()) {
            throw new ApiException("사장님만 가게를 만들 수 있습니다.", HttpStatus.FORBIDDEN);
        }

        long storeCount = storeRepository.countByOwnerAndIsDeletedFalse(owner);
        if (storeCount >= 3) {
            throw new ApiException("사장님은 가게를 최대 3개까지만 운영할 수 있습니다.", HttpStatus.BAD_REQUEST);
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
                .orElseThrow(() -> new ApiException("가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        List<Menu> menus = menuRepository.findByStoreIdAndIsDeletedFalse(storeId);
        List<MenuDto> menuDtos = menus.stream()
                .map(MenuDto::menuDto)
                .collect(Collectors.toList());

        return StoreMenuResponseDto.of(store, menuDtos);
    }

    @Transactional
    public StoreResponseDto updateStore(Long storeId, StoreRequestDto requestDto, Owner owner) {
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> new ApiException("가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        if (!store.getOwner().equals(owner)) {
            throw new ApiException("가게를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        store.update(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime());
        return StoreResponseDto.of(store);
    }

    @Transactional
    public ResponseEntity<String> deleteStore(Long storeId, Owner owner) {
        Store store = storeRepository.findByIdAndIsDeletedFalse(storeId)
                .orElseThrow(() -> new ApiException("가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        if (!store.getOwner().equals(owner)) {
            throw new ApiException("가게를 폐업할 권한이 없습니다.", HttpStatus.NOT_FOUND);
        }

        store.setDeleted(true);
        return ResponseEntity.noContent().build();
    }
}
