package team7.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.user.UserCreateRequestDto;
import team7.delivery.dto.user.UserCreateResponseDto;
import team7.delivery.dto.user.UserDeleteRequestDto;
import team7.delivery.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<UserCreateResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto dto) {

        UserCreateResponseDto user = userService.createUser(dto.getEmail(), dto.getPassword());

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id, @Valid @RequestBody UserDeleteRequestDto dto){

        userService.deactivateUser(id, dto.getEmail(), dto.getPassword());

        return ResponseEntity.ok("사용자가 정상적으로 삭제되었습니다.");
    }


}
