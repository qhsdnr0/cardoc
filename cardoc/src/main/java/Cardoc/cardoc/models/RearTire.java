package Cardoc.cardoc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
@Getter @Setter
public class RearTire extends Tire {

    private String name;
}
