package Cardoc.cardoc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String account;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Trim> trims = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
