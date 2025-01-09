package team7.delivery.repository;

//import com.example.memo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
