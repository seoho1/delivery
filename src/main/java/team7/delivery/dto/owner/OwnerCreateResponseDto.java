package team7.delivery.dto.owner;


import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team7.delivery.entity.Owner;

@RequiredArgsConstructor
@Getter
public class OwnerCreateResponseDto {

    private final String email;

    private final String createdAt;

    private final String updatedAt;

    public static OwnerCreateResponseDto of(Owner owner){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new OwnerCreateResponseDto(
                owner.getEmail(),
                owner.getCreatedAt().format(dtf),
                owner.getUpdated_at().format(dtf)
        );
    }
}
