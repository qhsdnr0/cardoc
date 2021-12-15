package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
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
    private Long id;

    @Column(unique = true)
    private String account;

    private String password;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<UserTrimTire> userTrimTires = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
