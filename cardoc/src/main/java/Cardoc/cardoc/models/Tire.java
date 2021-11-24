package Cardoc.cardoc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tires")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tire_type")
@Getter @Setter
public abstract class Tire {

    @Id @GeneratedValue
    @Column(name = "tire_id")
    private Long id;

    private int flat;
    private int width;
    private int wheelSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id")
    private Trim trim;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
