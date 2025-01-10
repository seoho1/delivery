package team7.delivery.service;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team7.delivery.dto.review.ReviewRequestDto;
import team7.delivery.dto.review.ReviewResponseDto;
import team7.delivery.entity.Order;
import team7.delivery.entity.Review;
import team7.delivery.entity.Store;
import team7.delivery.entity.User;
import team7.delivery.exception.StoreException;
import team7.delivery.repository.OrderRepository;
import team7.delivery.repository.ReviewRepository;
import team7.delivery.repository.StoreRepository;
import team7.delivery.repository.UserRepository;

import java.util.List;

@Getter
@Service
@RequiredArgsConstructor

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    public ReviewResponseDto createReview(ReviewRequestDto request, long userId, HttpSession session){

        Long orderId = (Long) session.getAttribute("orderId");

        if (orderId == null) {
            throw new StoreException("주문 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->  new StoreException("유저가 없습니다.", HttpStatus.NOT_FOUND));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->  new StoreException("주문이 없습니다.", HttpStatus.NOT_FOUND));
//        if (!order.getStatus().equals(OrderStatus.COMPLETED)) {
//            throw new StoreException("배달 완료 되지 않은 주문은 리뷰를 작성할 수 없습니다.", HttpStatus.BAD_REQUEST);
//        }
        if (request.getRate()<1 || request.getRate()>5) {
            throw new StoreException("별점 점수에 해당하지 않습니다.",HttpStatus.BAD_REQUEST);
        }
        Review review = Review.of(request,user,order);
        reviewRepository.save(review);
        return ReviewResponseDto.convertDto(review);


    }
    public List<ReviewResponseDto> getReview(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new StoreException("가게 정보 오류 or 단건 조회 입니다.",HttpStatus.NOT_FOUND));

        List<Review> review = reviewRepository.findByStoreOrderByCreatedAtDesc(store);
        return review.stream()
                .map(ReviewResponseDto::convertDto)
                .toList();
    }






}
