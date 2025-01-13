package team7.delivery.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.delivery.dto.review.ReviewRequestDto;
import team7.delivery.dto.review.ReviewResponseDto;
import team7.delivery.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto request, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        return new ResponseEntity<>(reviewService.createReview(request, userId, session), HttpStatus.OK);

    }

    @GetMapping("/store/{storeId}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReview(@PathVariable Long storeId){
        return new ResponseEntity<>(reviewService.getReview(storeId),HttpStatus.OK);
    }

}


