package team7.delivery.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class MenuRequestDto {
    @NotNull
    private Integer store_id;
    private String name;
    private int price;
    private String describe;

    public MenuRequestDto(Integer store_id, String name, int price, String describe) {
        this.store_id = store_id;
        this.name = name;
        this.price = price;
        this.describe = describe;
    }

    public Integer getStore_id() {
        return store_id;
    }
}



