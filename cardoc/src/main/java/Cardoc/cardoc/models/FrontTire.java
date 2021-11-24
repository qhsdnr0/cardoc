package Cardoc.cardoc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
@Getter @Setter
public class FrontTire extends Tire {

    private String name;
}
