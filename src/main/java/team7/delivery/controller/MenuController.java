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

//    private final MenuService menuService;
//    @PostMapping
//    public ResponseEntity<MenuDto> CreateMemo(@Valid @RequestBody MenuRequestDto request, HttpSession session){
//        return new ResponseEntity<>(menuService.CreateMemo(request), HttpStatus.CREATED);
//    }

//    @PutMapping("/{menusId}")
//    public ResponseEntity<MenuDto> updateMemo(@PathVariable Long storeId,@Valid @RequestBody MenuRequestDto request){
//        return new ResponseEntity<>(menuService.updateMemo())
//    }
//
//    @PutMapping("/{commentId}")
//    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId,@Valid @RequestBody UpdateCommentRequest request) {
//        return new ResponseEntity<>(commentService.updateComment(commentId,request), HttpStatus.OK);
//    }

}


