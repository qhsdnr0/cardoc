package Cardoc.cardoc.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TrimForm {

    @NotEmpty(message = "NAME_CANNOT_BE_EMPTY")
    private String name;

    private Long carId;
    
}
