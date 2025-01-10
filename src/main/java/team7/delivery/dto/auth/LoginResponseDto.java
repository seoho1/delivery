package team7.delivery.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team7.delivery.entity.User;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {

    private final Long id;

    private final String email;

    private final String role;


    public static LoginResponseDto of(User user) {
        return new LoginResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
