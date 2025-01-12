package team7.delivery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import team7.delivery.dto.auth.Role;

@Entity
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    protected User(){

    }

    private User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public static User of(String email, String password) {
        return new User(email, password);
    }

    public void deactivate() {
        this.isDeleted = true;
    }
}
