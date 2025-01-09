package team7.delivery.dto.order;

public class CreateOrderRequestDto {

    private final Long userId;
    private final Long menuId;

    public CreateOrderRequestDto(Long userId, Long menuId) {
        this.userId = userId;
        this.menuId = menuId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMenuId() {
        return menuId;
    }
}
