package Cardoc.cardoc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trims")
@Getter @Setter
public class Trim {

    @Id @GeneratedValue
    @Column(name = "trim_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "trim")
    private List<Tire> tires = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
