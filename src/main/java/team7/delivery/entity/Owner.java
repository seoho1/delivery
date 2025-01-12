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


    @Column(unique = true)
    private String email;

    private String password;

    @Column
    private boolean isOwner;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private Role role = Role.OWNER;

    private Owner(String email, String password, boolean isOwner) {
        this.email = email;
        this.password = password;
        this.isOwner = isOwner;
    }

    public static Owner of(String email, String password, boolean isOwner) {
        return new Owner(email, password, isOwner);
    }

    public void deactivate() {
        this.isDeleted = true;
    }

    public boolean isOwner() {
        return this.isOwner;

    }
}
