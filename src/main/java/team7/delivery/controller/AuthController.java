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
import team7.delivery.dto.auth.SigninRequestDto;
import team7.delivery.dto.auth.SigninResponseDto;
import team7.delivery.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signin(@Valid @RequestBody SigninRequestDto dto, HttpSession session) {
        SigninResponseDto user = authService.signin(dto.getEmail(), dto.getPassword());
        session.setAttribute("user", user.getId());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
