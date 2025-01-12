package team7.delivery.dto.store;

import lombok.Getter;
import team7.delivery.entity.Store;

@Getter
public class StoreResponseDto {

    private final Long id;
    private final String storeName;
    private final Integer minPrice;
    private final String openTime;
    private final String closeTime;
    private final boolean isDeleted;
//    private final List<MenuResponseDto> menus;

    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.minPrice = store.getMinPrice();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.isDeleted = store.isDeleted();
//        this.menus = menus;
    }

    public static StoreResponseDto of(Store store) {
        return new StoreResponseDto(store);
    }
}
