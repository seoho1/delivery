package team7.delivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {

    private final Long userId;

    private final Long menuId;

    private final String status;

}
