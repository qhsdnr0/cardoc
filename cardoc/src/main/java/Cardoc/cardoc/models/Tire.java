package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tires")

@Getter @Setter
public class Tire {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private TireInfo tireInfo;

    @OneToMany(mappedBy = "tire")
    @JsonBackReference
    private List<TrimTire> trimTires = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
