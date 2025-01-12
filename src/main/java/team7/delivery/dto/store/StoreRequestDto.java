package team7.delivery.dto.store;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreRequestDto {

    private final Long ownerId;

    @NotNull(message = "가게 이름은 필수입니다.")
    private final String storeName;

    @NotNull(message = "최소 주문 금액은 필수입니다.")
    @Min(value = 10000, message = "최소 주문 금액은 10000 이상이어야 합니다.")
    private final Integer minPrice;

    @NotNull(message = "영업 시작 시간은 필수입니다")
    private final LocalTime openTime;

    @NotNull(message = "영업 종료 시간은 필수입니다.")
    private final LocalTime closeTime;

}
