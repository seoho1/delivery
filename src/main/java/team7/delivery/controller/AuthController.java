package team7.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.delivery.dto.auth.SigninRequestDto;
import team7.delivery.dto.auth.SigninResponseDto;
import team7.delivery.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public RequestEntity<SigninResponseDto> signin(@Valid @RequestBody SigninRequestDto dto) {
        return authService.signin(dto);
    }

}
