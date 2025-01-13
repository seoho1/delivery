package team7.delivery.dto.review;


import lombok.Getter;

@Getter
public class ReviewRequestDto {

    private final String comment;

    private final Long rate;

    private final Long orderId;

    public ReviewRequestDto(String comment, Long rate, Long orderId) {
        this.comment = comment;
        this.rate = rate;
        this.orderId = orderId;
    }
}
