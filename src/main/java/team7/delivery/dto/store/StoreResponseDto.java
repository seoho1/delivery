package team7.delivery.dto.store;

import lombok.Getter;
import team7.delivery.entity.Store;

import java.time.LocalTime;

@Getter
public class StoreResponseDto {

    private final Long id;
    private final String storeName;
    private final Integer minPrice;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final boolean isDeleted;

    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.minPrice = store.getMinPrice();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.isDeleted = store.isDeleted();
    }

    public static StoreResponseDto of(Store store) {
        return new StoreResponseDto(store);
    }
}
