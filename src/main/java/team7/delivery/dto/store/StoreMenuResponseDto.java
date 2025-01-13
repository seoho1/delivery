package team7.delivery.dto.store;

import lombok.Getter;
import team7.delivery.dto.menu.MenuDto;
import team7.delivery.entity.Store;

import java.util.List;

/**
 * StoreMenuResponseDto
 */
@Getter
public class StoreMenuResponseDto extends StoreResponseDto {

    /**
     * 메뉴 목록
     */
    private final List<MenuDto> menus;

    /**
     * 생성자
     *
     * @param store 엔티티
     * @param menus 목록
     */
    public StoreMenuResponseDto(Store store, List<MenuDto> menus) {
        super(store); // 부모 클래스 초기화
        this.menus = menus;
    }

    /**
     * 정적 팩토리
     *
     * @param store 엔티티
     * @param menus 목록
     * @return 생성된 StoreMenuResponseDto 객체
     */
    public static StoreMenuResponseDto of(Store store, List<MenuDto> menus) {
        return new StoreMenuResponseDto(store, menus);
    }
}
