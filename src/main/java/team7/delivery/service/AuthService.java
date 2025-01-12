package team7.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.delivery.config.CustomPasswordEncoder;
import team7.delivery.dto.auth.LoginResponseDto;
import team7.delivery.entity.Owner;
import team7.delivery.entity.User;
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
import team7.delivery.repository.OwnerRepository;
import team7.delivery.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final CustomPasswordEncoder passwordEncoder;


    public LoginResponseDto login(String email, String password) {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));

        if(!passwordEncoder.matches(foundUser.getPassword(), password)) {
            ExceptionUtil.throwErrorMessage(ErrorMessage.PASSWORD_IS_WRONG, ApiException.class);
        }

        return LoginResponseDto.of(foundUser);
    }

    public LoginResponseDto ownerLogin(String email, String password) {
        Owner foundOwner = ownerRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class));

        if(!passwordEncoder.matches(foundOwner.getPassword(), password)) {
            ExceptionUtil.throwErrorMessage(ErrorMessage.PASSWORD_IS_WRONG, ApiException.class);
        }

        return LoginResponseDto.of(foundOwner);
    }


}
