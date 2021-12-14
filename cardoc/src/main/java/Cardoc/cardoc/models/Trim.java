package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trims")
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trim {

    @Id @GeneratedValue
    @Column(name = "trim_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "trim")
    @JsonBackReference
    private List<UserTrim> userTrims = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private Car car;

    @OneToMany(mappedBy = "trim")
    @JsonBackReference
    private List<Tire> tires = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addTire(Tire tire) {
        tire.setTrim(this);
        this.getTires().add(tire);
    }

    public void addCar(Car car) {
        car.getTrims().add(this);
        this.setCar(car);
    }
}
