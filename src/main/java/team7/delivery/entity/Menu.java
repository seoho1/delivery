package team7.delivery.entity;

//import com.example.memo.dto.MenuRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team7.delivery.dto.menu.MenuRequestDto;

@Getter
@AllArgsConstructor
@Entity
public class Menu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    private String description;
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    protected Menu() {

    }



    public static Menu of(MenuRequestDto request, Store store) {
        return new Menu(null, request.getName(),request.getPrice(),request.getDescribe(),false, store);
    }
}
