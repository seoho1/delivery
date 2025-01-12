package team7.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team7.delivery.dto.auth.Role;

@Entity
@Getter
@Table(name = "owners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private Role role = Role.OWNER;

    private Owner(String email, String password) {
        this.email = email;
        this. password = password;
    }

    public static Owner of(String email, String password){
        return new Owner(email, password);
    }

    public void deactivate() {
        this.isDeleted = true;
    }
}
