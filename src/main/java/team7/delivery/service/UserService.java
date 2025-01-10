package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.dto.auth.Role;
import team7.delivery.config.PasswordEncoder;
import team7.delivery.dto.user.UserCreateResponseDto;
import team7.delivery.entity.User;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreateResponseDto createUser(String email, String password, Role role) {

        checkRegisteredUser(email);

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.of(email, encodedPassword);

        User savedUser = userRepository.save(user);

        return UserCreateResponseDto.of(savedUser);
    }

    public void checkRegisteredUser(String email) {
        if(userRepository.findByEmail(email).isPresent()){
            ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class);
        }
    }

    public void deactivateUser(Long id, String email, String password) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));

        foundUser.deactivate();
        userRepository.save(foundUser);
    }

}
