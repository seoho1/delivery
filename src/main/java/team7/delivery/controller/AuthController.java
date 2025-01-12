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
import team7.delivery.entity.Owner;
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
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/owenr/signin")
    public ResponseEntity<LoginResponseDto> ownerLogin(@Valid @RequestBody LoginRequestDto dto, HttpSession session) {
        LoginResponseDto owner = authService.ownerLogin(dto.getEmail(), dto.getPassword());
        session.setAttribute("user", owner.getId());
        session.setAttribute("email", owner.getEmail());
        session.setAttribute("role", owner.getRole());
        Owner createdOwner = Owner.of(dto.getEmail(), dto.getPassword());
        session.setAttribute("owner", createdOwner);

        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    public void setOwnerInSession(Owner owner, HttpSession session) {
        session.setAttribute("owner", owner);
    }

}
