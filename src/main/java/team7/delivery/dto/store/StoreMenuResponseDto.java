package team7.delivery.dto.store;

import lombok.Getter;
import team7.delivery.dto.menu.MenuDto;
import team7.delivery.entity.Store;

import java.util.List;

@Getter
public class StoreMenuResponseDto extends StoreResponseDto {

    private final List<MenuDto> menus;

    public StoreMenuResponseDto(Store store, List<MenuDto> menus) {
        super(store);
        this.menus = menus;
    }

    public static StoreMenuResponseDto of(Store store, List<MenuDto> menus) {
        return new StoreMenuResponseDto(store, menus);
    }
}
