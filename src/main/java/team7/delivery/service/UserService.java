package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team7.delivery.dto.user.UserCreateResponseDto;
import team7.delivery.entity.User;
import team7.delivery.exception.CustomException;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponseDto createUser(String email, String password) {

        checkRegisteredUser(email);

        User user = User.of(email, password);

        User savedUser = userRepository.save(user);

        return UserCreateResponseDto.of(savedUser);
    }

    public void checkRegisteredUser(String email) {
        User foundUser = userRepository.findByEmail(email).orElse(null);

        if (foundUser == null) {
            return;
        }

        throw new CustomException(HttpStatus.BAD_REQUEST, "등록하려는 이메일이 이미 존재합니다");
    }

}
