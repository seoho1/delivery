package team7.delivery.controller;

//import com.example.memo.dto.MenuDto;
//import com.example.memo.dto.MenuRequestDto;
//import com.example.memo.service.MemoServide;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.delivery.dto.menu.MenuDto;
import team7.delivery.dto.menu.MenuRequestDto;
import team7.delivery.service.MenuService;

@RestController
@RequestMapping("/api/stores/{storeId}/menus") // prefix
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@Valid @RequestBody MenuRequestDto request, HttpSession session){
        return new ResponseEntity<>(menuService.createMenu(request), HttpStatus.CREATED);
    }

    @GetMapping("/{menusId}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long menusId) {
        return new ResponseEntity<>(menuService.getMenu(menusId), HttpStatus.OK);
    }

    @PutMapping("/{menusId}")
    public ResponseEntity<MenuDto> updateMemu(@PathVariable Long menusId,@Valid @RequestBody MenuRequestDto request){
        return new ResponseEntity<>(menuService.updateMenu(menusId,request), HttpStatus.OK);
    }

    @DeleteMapping("/{menusId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menusId){
        menuService.deleteMenu(menusId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


