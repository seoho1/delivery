package team7.delivery.repository;

//import com.example.memo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
