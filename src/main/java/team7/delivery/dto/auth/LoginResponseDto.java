package team7.delivery.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team7.delivery.entity.Owner;
import team7.delivery.entity.User;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {

    private final Long id;

    private final String email;

    private final Role role;


    public static LoginResponseDto of(User user) {
        return new LoginResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    public static LoginResponseDto of(Owner owner){
        return new LoginResponseDto(
                owner.getId(),
                owner.getEmail(),
                owner.getRole()
        );
    }

}
