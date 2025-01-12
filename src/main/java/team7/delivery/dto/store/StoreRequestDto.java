package team7.delivery.dto.store;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreRequestDto {

    private final Long ownerId;

    @NotNull(message = "가게 이름은 필수입니다.")
    private final String storeName;

    @Min(value = 10000, message = "최소 주문 금액은 10000 이상이어야 합니다.")
    private final Integer minPrice;

    @NotBlank
    private final String openTime;

    @NotBlank
    private final String closeTime;

}
