package team7.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Review;
import team7.delivery.entity.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByStoreOrderByCreatedAtDesc(Store store);
}
