package team7.delivery.service;

import java.util.Optional;
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

    public UserCreateResponseDto createUser(String email, String password, String role) {

        checkRegisteredUser(email);

        User user = User.of(email, password, role);

        User savedUser = userRepository.save(user);

        return UserCreateResponseDto.of(savedUser);
    }

    public void checkRegisteredUser(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        System.out.println("findByEmail 결과: " + user);
        if(userRepository.findByEmail(email).isPresent()){
            throw new CustomException(HttpStatus.BAD_REQUEST,"이미 등록된 사용자입니다.");
        }
    }

    public void deactivateUser(Long id, String email, String password) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        foundUser.deactivate();
        userRepository.save(foundUser);
    }

}
