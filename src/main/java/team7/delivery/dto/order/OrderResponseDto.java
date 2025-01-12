package team7.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team7.delivery.entity.Order;
import team7.delivery.status.OrderStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private final Long id;
    private final Long userId;
    private final Long menuId;
    private final OrderStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private boolean isDeleted; // 메뉴 삭제 여부



    public static OrderResponseDto of(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdated_at(),
                order.getMenu().isDeleted()
        );
    }
}
