package team7.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.User;

public interface UserRepository extends JpaRepository <User, Long> {

}
