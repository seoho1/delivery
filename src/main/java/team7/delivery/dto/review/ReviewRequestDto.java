package team7.delivery.dto.review;


import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private final String comment;
    private final Long rate;

    public ReviewRequestDto(String comment, Long rate) {
        this.comment = comment;
        this.rate = rate;
    }
}
