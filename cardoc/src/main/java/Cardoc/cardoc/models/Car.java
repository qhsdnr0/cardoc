package Cardoc.cardoc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter
public class Car {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "car")
    @JsonBackReference
    private List<Trim> trims = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
