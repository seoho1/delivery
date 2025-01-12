package team7.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDto {

    private final Long userId;
    private final Long menuId;

}
