package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "trim_tires")
@Getter @Setter
public class TrimTire {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id")
    @JsonManagedReference
    private Trim trim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tire_id")
    @JsonManagedReference
    private Tire tire;

    public void addTrim(Trim trim, Tire tire) {
        this.setTrim(trim);
        this.setTire(tire);
        trim.getTrimTires().add(this);
        tire.getTrimTires().add(this);
    }
}
