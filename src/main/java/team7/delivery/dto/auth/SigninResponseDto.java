package team7.delivery.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team7.delivery.entity.User;

@Getter
@RequiredArgsConstructor
public class SigninResponseDto {

    private final Long id;

    private final String email;

    private final String role;


    public static SigninResponseDto of(User user) {
        return new SigninResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
