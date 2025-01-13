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
    private Long Id;
    private String name;
    private int price;

    private String description;
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    protected Menu() {

    }

    private Menu(String name, int price, String description, boolean isDeleted, Store store) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isDeleted = isDeleted;
        this.store = store;
    }

    public static Menu of(MenuRequestDto request, Store store) {
        return new Menu(request.getName(),request.getPrice(),request.getDescribe(),false, store);
    }

    public void update(String name, int price, String description){
        this.name = name;
        this.price = price;
        this.description =description;
    }

    public void delete() {
        this.isDeleted = true;
    }

}
