package team7.delivery.dto.menu;

import lombok.Getter;

@Getter
public class MenuRequestDto {

    private Long storeId;
    private String name;
    private int price;
    private String describe;

    public MenuRequestDto(Long storeId ,String name, int price, String describe) {
        this.storeId = storeId;
        this.name = name;
        this.price = price;
        this.describe = describe;
    }

    public MenuRequestDto() {
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    // static factory method
    public static MenuRequestDto of(Long storeId) {
        MenuRequestDto dto = new MenuRequestDto();
        dto.setStoreId(storeId);
        return dto;
    }
}

