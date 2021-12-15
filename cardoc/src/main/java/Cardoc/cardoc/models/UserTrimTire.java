package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "user_trim_tires")
@Getter @Setter
public class UserTrimTire {

    @Id @GeneratedValue
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id")
    @JsonManagedReference
    private Trim trim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tire_id")
    @JsonManagedReference
    private Tire tire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    public void addUserTrimTire(Trim trim, Tire tire, User user) {
        this.setTrim(trim);
        this.setTire(tire);
        this.setUser(user);
        trim.getUserTrimTires().add(this);
        tire.getUserTrimTires().add(this);
        user.getUserTrimTires().add(this);
    }
}
