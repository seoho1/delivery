package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team7.delivery.dto.store.StoreRequestDto;
import team7.delivery.dto.store.StoreResponseDto;
import team7.delivery.entity.Owner;
import team7.delivery.entity.Store;
import team7.delivery.exception.StoreException;
import team7.delivery.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResponseDto createStore(StoreRequestDto requestDto, Owner owner) {

        Store store = Store.of(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime(), owner);

        Store savedStore = storeRepository.save(store);

        return StoreResponseDto.of(savedStore);
    }

    public List<StoreResponseDto> getStoresByName(String storeName) {
        List<Store> stores = storeRepository.findByStoreNameContaining(storeName);
        return stores.stream()
                .map(StoreResponseDto::of)
                .collect(Collectors.toList());
    }

    public StoreResponseDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException("가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        return StoreResponseDto.of(store);
    }

    @Transactional
    public StoreResponseDto updateStore(Long storeId, StoreRequestDto requestDto, Owner owner) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException("가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        if (!store.getOwner().equals(owner)) {
            throw new StoreException("가게를 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        store.update(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime());

        Store savedStore = storeRepository.save(store);
        return StoreResponseDto.of(savedStore);
    }
}
