package team7.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
