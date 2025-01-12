package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.config.CustomPasswordEncoder;
import team7.delivery.dto.auth.LoginResponseDto;
import team7.delivery.entity.User;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder passwordEncoder;


    public LoginResponseDto login(String email, String password) {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));

        if(!passwordEncoder.matches(foundUser.getPassword(), password)) {
            ExceptionUtil.throwErrorMessage(ErrorMessage.PASSWORD_IS_WRONG, ApiException.class);
        }

        return LoginResponseDto.of(foundUser);
    }
}
