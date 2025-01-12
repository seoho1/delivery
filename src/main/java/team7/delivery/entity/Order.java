package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import team7.delivery.status.OrderStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status;

    private LocalDateTime orderTime;

    public Order(User user, Menu menu) {
        super();
    }

    public Order() {

    }

    public Order(OrderStatus status, Order order) {

        super();
    }
}
