package team7.delivery.repository;

//import com.example.memo.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team7.delivery.entity.Menu;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {


    @Query("SELECT m FROM Menu m WHERE m.Id = :menuId AND m.isDeleted = false")
    Optional<Menu> findActiveMenuById(@Param("menuId") Long menuId);
}
