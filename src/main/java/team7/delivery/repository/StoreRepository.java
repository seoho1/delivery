package team7.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team7.delivery.entity.Owner;
import team7.delivery.entity.Store;

import java.util.List;
import java.util.Optional;

/**
 * StoreRepository
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

    /**
     * 가게 이름으로 검색
     *
     * @param storeName 검색할 가게 이름
     * @return 가게 목록
     */
    List<Store> findByStoreNameContainingAndIsDeletedFalse(String storeName);

    /**
     * 가게 ID로 조회
     *
     * @param storeId 가게 ID
     * @return Optional로 래핑된 가게 정보
     */
    Optional<Store> findByIdAndIsDeletedFalse(Long storeId);

    /**
     * 사장의 가게 수를 카운트
     *
     * @param owner 가게 사장
     * @return 가게 갯수
     */
    long countByOwnerAndIsDeletedFalse(Owner owner);
}
