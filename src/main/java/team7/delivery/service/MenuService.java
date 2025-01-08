package team7.delivery.service;

//import com.example.menu.dto.MenuDto;
//import com.example.menu.dto.MenuRequestDto;
//import com.example.menu.entity.Menu;
//import com.example.menu.entity.Store;
//import com.example.menu.repository.MenuRepository;
//import com.example.menu.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team7.delivery.dto.menu.MenuDto;
import team7.delivery.dto.menu.MenuRequestDto;
import team7.delivery.entity.Menu;
import team7.delivery.exception.StoreException;
import team7.delivery.repository.MenuRepository;
import team7.delivery.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public MenuDto CreateMemo(MenuRequestDto request/* int store_id*/){
        Store store = storeRepository.findById(request.getStore_id()).orElseThrow(() -> new StoreException("가게가 없습니다.", HttpStatus.NOT_FOUND));
        Menu menu = Menu.of(request,store);
        menuRepository.save(menu);
        return menuDto(menu);
    }

    private MenuDto menuDto(Menu menu) {
        return MenuDto.menuDto(menu);
    }

}
