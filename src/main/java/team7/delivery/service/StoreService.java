package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.dto.store.StoreRequestDto;
import team7.delivery.dto.store.StoreResponseDto;
import team7.delivery.entity.Owner;
import team7.delivery.entity.Store;
import team7.delivery.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResponseDto createStore(StoreRequestDto requestDto, Owner owner) {

        Store store = Store.of(requestDto.getStoreName(), requestDto.getMinPrice(), requestDto.getOpenTime(), requestDto.getCloseTime(), owner);

        Store savedStore = storeRepository.save(store);

        return StoreResponseDto.of(savedStore);
    }
}
