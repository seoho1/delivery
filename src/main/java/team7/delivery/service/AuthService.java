package team7.delivery.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import team7.delivery.dto.auth.SigninRequestDto;
import team7.delivery.dto.auth.SigninResponseDto;
import team7.delivery.entity.User;
import team7.delivery.exception.CustomException;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    public RequestEntity<SigninResponseDto> signin(@Valid SigninRequestDto dto) {
        User foundUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "가입되지 않은 유저입니다"));

        if(dto.getPassword().equals(foundUser.getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 입니다.");
        }
        return null;
    }
}
