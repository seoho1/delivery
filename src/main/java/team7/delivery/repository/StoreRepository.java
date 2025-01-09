package team7.delivery.repository;

//import com.example.memo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStoreNameContaining (String storeName);
}
