package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_trims")
@Getter @Setter
public class UserTrim {

    @Id @GeneratedValue
    @Column(name = "user_trim_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id")
    @JsonManagedReference
    private Trim trim;

    public static UserTrim addUserTrim(User user, Trim trim) {
        UserTrim userTrim = new UserTrim();
        userTrim.setUser(user);
        userTrim.setTrim(trim);

        return userTrim;
    }
}
