package team7.delivery.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Owner;
import team7.delivery.entity.User;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<User> findByEmail(String email);

}
