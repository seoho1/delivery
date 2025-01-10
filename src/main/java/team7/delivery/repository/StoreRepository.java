package team7.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Owner;
import team7.delivery.entity.Store;
import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStoreNameContainingAndIsDeletedFalse(String storeName);
    Optional<Store> findByIdAndIsDeletedFalse(Long storeId);
    long countByOwnerAndIsDeletedFalse(Owner owner);

}
