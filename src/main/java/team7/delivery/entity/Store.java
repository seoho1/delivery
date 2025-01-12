package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String storeName;
    
    @Column
    private int minPrice;

    @Column
    private boolean isDeleted;

    @Column
    private LocalTime openTime;

    @Column
    private LocalTime closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    public Store(String storeName, int minPrice, LocalTime openTime, LocalTime closeTime, Owner owner) {
        this.storeName = storeName;
        this.minPrice = minPrice;
        this.isDeleted = false;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
    }

    public void update(String storeName, int minPrice, LocalTime openTime, LocalTime closeTime) {
        this.storeName = storeName;
        this.minPrice = minPrice;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

        public static Store of (String storeName,int minPrice, LocalTime openTime, LocalTime closeTime, Owner owner) {
            return new Store(storeName, minPrice, openTime, closeTime, owner);
    }

}
