package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team7.delivery.dto.review.ReviewRequestDto;

@AllArgsConstructor
@Entity
@Getter
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Long rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    protected Review() {
    }

    public static Review of(ReviewRequestDto request, User user, Order order) {
        return new Review(
                null,
                request.getComment(),
                request.getRate(),
                null,
                null,
                null
        );
    }

//    public static Review off(Store store){
//        return new Review(
//                null,
//                null,
//                null,
//                null,
//                null,
//                store
//        );
}
//






