package team7.delivery.dto.store;

import lombok.Getter;
import team7.delivery.entity.Store;

@Getter
public class StoreRequestDto {

    private final Long ownerId;
    private final String storeName;
    private final Integer minPrice;
    private final String openTime;
    private final String closeTime;

    public StoreRequestDto(Long ownerId, String storeName, Integer minPrice, String openTime, String closeTime) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.minPrice = minPrice;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
