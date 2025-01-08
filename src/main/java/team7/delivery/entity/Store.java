package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "stores")
@EntityListeners(AuditingEntityListener.class)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String openTime;

    @Column
    private String closeTime;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;*/

public Store(String storeName, int minPrice, String openTime, String closeTime, Owner owner) {
    this.storeName = storeName;
    this.minPrice = minPrice;
    this.isDeleted = false;
    this.openTime = openTime;
    this.closeTime = closeTime;
//    this.owner = owner;
}
//
//    public static Store of(String storeName, int minPrice, String openTime, String closeTime, Owner owner) {
//    return new Store(storeName, minPrice, openTime, closeTime, owner};{
//
//    }
}
