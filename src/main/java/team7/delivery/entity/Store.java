package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Store 엔티티
 * BaseEntity를 상속받음
 */
@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자는 접근 제한자 PROTECTED로 설정
public class Store extends BaseEntity {

    /**
     * 가게 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 ID 설정
    private Long id;

    /**
     * 가게 이름
     */
    @Column
    private String storeName;

    /**
     * 최소 주문 금액
     */
    @Column
    private int minPrice;

    /**
     * 삭제 여부
     */
    @Column
    private boolean isDeleted;

    /**
     * 오픈 시간
     */
    @Column
    private LocalTime openTime;

    /**
     * 마감 시간
     */
    @Column
    private LocalTime closeTime;

    /**
     * 가게 사장
     * Owner 엔티티와 다대일 관계
     */
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "owner_id", nullable = false) // 외래 키 이름 설정
    private Owner owner;

    /**
     * 가게 생성자
     *
     * @param storeName 가게 이름
     * @param minPrice  최소 주문 금액
     * @param openTime  오픈 시간
     * @param closeTime 마감 시간
     * @param owner     가게 사장
     */
    public Store(String storeName, int minPrice, LocalTime openTime, LocalTime closeTime, Owner owner) {
        this.storeName = storeName;
        this.minPrice = minPrice;
        this.isDeleted = false;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
    }

    /**
     * 가게 정보 수정
     *
     * @param storeName 변경할 가게 이름
     * @param minPrice  변경할 최소 주문 금액
     * @param openTime  변경할 오픈 시간
     * @param closeTime 변경할 마감 시간
     */
    public void update(String storeName, int minPrice, LocalTime openTime, LocalTime closeTime) {
        this.storeName = storeName;
        this.minPrice = minPrice;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    /**
     * 가게 삭제 상태 변경
     *
     * @param deleted 삭제 상태
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * 정적 팩토리
     * Store 객체 생성할 때 사용
     *
     * @param storeName 가게 이름
     * @param minPrice  최소 주문 금액
     * @param openTime  오픈 시간
     * @param closeTime 마감 시간
     * @param owner     가게 사장
     * @return 생성된 Store 객체
     */
    public static Store of(String storeName, int minPrice, LocalTime openTime, LocalTime closeTime, Owner owner) {
        return new Store(storeName, minPrice, openTime, closeTime, owner);
    }
}
