package team7.delivery.dto.store;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

/**
 * StoreRequestDto
 */
@Getter
@RequiredArgsConstructor
public class StoreRequestDto {

    /**
     * 사장의 ID
     */
    private final Long ownerId;

    /**
     * 가게 이름
     */
    @NotNull(message = "가게 이름은 필수입니다.")
    private final String storeName;

    /**
     * 최소 주문 금액(최소 10,000원 이상)
     */
    @NotNull(message = "최소 주문 금액은 필수입니다.")
    @Min(value = 10000, message = "최소 주문 금액은 10000 이상이어야 합니다.")
    private final Integer minPrice;

    /**
     * 영업 시작 시간
     */
    @NotNull(message = "영업 시작 시간은 필수입니다")
    private final LocalTime openTime;

    /**
     * 영업 종료 시간
     */
    @NotNull(message = "영업 종료 시간은 필수입니다.")
    private final LocalTime closeTime;
}
