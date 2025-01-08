package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.dto.user.UserCreateResponseDto;
import team7.delivery.entity.User;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserCreateResponseDto createUser(String email, String password) {

        User user = User.of(email, password);

        User savedUser = userRepository.save(user);

        return UserCreateResponseDto.of(savedUser);
    }
}

