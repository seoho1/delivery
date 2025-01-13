package team7.delivery.dto.store;

import lombok.Getter;
import team7.delivery.entity.Store;

import java.time.LocalTime;

/**
 * StoreResponseDto
 */
@Getter
public class StoreResponseDto {

    private final Long id;
    private final String storeName;
    private final Integer minPrice;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final boolean isDeleted;

    /**
     * 생성자
     *
     * @param store 엔티티를 기반으로 DTO 생성
     */
    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.minPrice = store.getMinPrice();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.isDeleted = store.isDeleted();
    }

    /**
     * 정적 팩토리
     *
     * @param store 엔티티
     * @return 생성된 StoreResponseDto 객체
     */
    public static StoreResponseDto of(Store store) {
        return new StoreResponseDto(store);
    }
}
