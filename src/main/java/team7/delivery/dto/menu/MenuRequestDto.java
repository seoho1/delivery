package team7.delivery.dto.menu;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MenuRequestDto {
    @NotNull
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
}

