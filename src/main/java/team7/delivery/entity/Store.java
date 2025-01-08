package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "stores")
@EntityListeners(AuditingEntityListener.class)
//@NoArgsConstructor(access = Access)
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
//
//    public static Store of(String storeName, int minPrice, String openTime, String closeTime, Owner owner){
//
//    }
}
