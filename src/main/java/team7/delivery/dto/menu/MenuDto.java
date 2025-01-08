package team7.delivery.dto.menu;

//import com.example.memo.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team7.delivery.entity.Menu;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private String name;
    private int price;
    private String describe;

    public static MenuDto menuDto(Menu menu) {
        return new MenuDto(
                menu.getName(),
                menu.getPrice(),
                menu.getDescription()
        );
    }
}
