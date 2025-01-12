package team7.delivery.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.auth.LoginRequestDto;
import team7.delivery.dto.auth.LoginResponseDto;
import team7.delivery.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto, HttpSession session) {
        LoginResponseDto user = authService.login(dto.getEmail(), dto.getPassword());
        session.setAttribute("user", user.getId());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
