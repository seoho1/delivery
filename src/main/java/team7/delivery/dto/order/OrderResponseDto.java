package team7.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

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


}
