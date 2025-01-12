package team7.delivery.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team7.delivery.entity.User;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Getter
public class UserCreateResponseDto {

    private final String email;

    private final String createdAt;

    private final String updatedAt;

    public static UserCreateResponseDto of(User user) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new UserCreateResponseDto(
                user.getEmail(),
                user.getCreatedAt().format(dtf),
                user.getUpdated_at().format(dtf)
        );
    }
}
