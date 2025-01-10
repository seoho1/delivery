package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "owners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean isOwner;

    public Owner(String email, String password, boolean isOwner) {
        this.email = email;
        this.password = password;
        this.isOwner = isOwner;
    }

    public boolean isOwner() {
        return this.isOwner;
    }
}
