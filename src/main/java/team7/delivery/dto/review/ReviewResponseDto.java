package team7.delivery.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team7.delivery.entity.Review;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private String comment;
    private Long rate;

    public static ReviewResponseDto convertDto(Review review){
        return new ReviewResponseDto(
                review.getComment(),
                review.getRate()
        );
    }
}
