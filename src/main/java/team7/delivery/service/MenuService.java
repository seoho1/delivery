package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.dto.menu.MenuDto;
import team7.delivery.dto.menu.MenuRequestDto;
import team7.delivery.entity.Menu;
import team7.delivery.entity.Store;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.MenuRepository;
import team7.delivery.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public MenuDto createMenu(MenuRequestDto request, Long StoreId){
        Store store = storeRepository.findById(StoreId).orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        Menu menu = Menu.of(request,store);
        menuRepository.save(menu);
        return menuDto(menu);
    }

    public MenuDto getMenu(Long menuId){
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() ->ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        return menuDto(menu);
    }

    public MenuDto updateMenu(Long menusId,MenuRequestDto request){
        Menu menu = menuRepository.findById(menusId)
                .orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        Menu.off(menusId,request);
        menuRepository.save(menu);
        return menuDto(menu);

    }
    public void deleteMenu(Long menusId){
        Menu menu = menuRepository.findActiveMenuById(menusId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));
        menu.delete();
        menuRepository.save(menu);
    }
    private MenuDto menuDto(Menu menu) {
        return MenuDto.menuDto(menu);
    }

}
