package team7.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team7.delivery.entity.Order;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private final Long id;
    private final Long userId;
    private final Long menuId;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;



    public static OrderResponseDto of(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdated_at()
        );
    }
}
