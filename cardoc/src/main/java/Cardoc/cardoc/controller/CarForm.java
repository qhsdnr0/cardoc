package Cardoc.cardoc.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CarForm {

    @NotEmpty(message = "NAME_CANNOT_BE_NULL")
    private String name;
}
