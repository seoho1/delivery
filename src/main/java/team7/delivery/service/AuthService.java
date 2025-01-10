package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team7.delivery.dto.auth.LoginResponseDto;
import team7.delivery.entity.User;
import team7.delivery.exception.CustomException;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    public LoginResponseDto login(String email, String password) {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "가입되지 않은 유저입니다"));

        if(!password.equals(foundUser.getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 입니다.");
        }

        return LoginResponseDto.of(foundUser);
    }
}
