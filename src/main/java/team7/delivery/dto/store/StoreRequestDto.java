package team7.delivery.dto.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreRequestDto {

    private final Long ownerId;
    private final String storeName;
    private final Integer minPrice;
    private final String openTime;
    private final String closeTime;

}
