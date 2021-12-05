package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tires")

@Getter @Setter
public class Tire {

    @Id @GeneratedValue
    @Column(name = "tire_id")
    private Long id;

    private String name;

    @Embedded
    private TireInfo tireInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "trim_id")
    @JsonIgnore
    private Trim trim;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
