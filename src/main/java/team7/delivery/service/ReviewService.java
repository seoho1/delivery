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
import team7.delivery.exception.ApiException;
import team7.delivery.exception.ExceptionUtil;
import team7.delivery.exception.util.ErrorMessage;
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

        Long orderId = request.getOrderId();

        if (orderId == null) {
            throw new ApiException("주문 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->  ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class)); //유저가 없습니다
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class)); //주문이 없습니다.

        if (request.getRate()<1 || request.getRate()>5) {
            throw new ApiException("별점 점수에 해당하지 않습니다.",HttpStatus.BAD_REQUEST);
        }
        Review review = Review.of(request,user,order);
        reviewRepository.save(review);
        return ReviewResponseDto.convertDto(review);


    }
    public List<ReviewResponseDto> getReview(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> ExceptionUtil.throwErrorMessage(ErrorMessage.ENTITY_NOT_FOUND, ApiException.class)); //가게 정보 오류

        List<Review> review = reviewRepository.findByStoreOrderByCreatedAtDesc(store);
        return review.stream()
                .map(ReviewResponseDto::convertDto)
                .toList();
    }






}
