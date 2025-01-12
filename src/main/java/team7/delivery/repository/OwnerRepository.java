package team7.delivery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
