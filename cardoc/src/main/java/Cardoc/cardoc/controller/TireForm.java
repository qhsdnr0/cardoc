package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.TireInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TireForm {

    private String name;

    @NotEmpty(message = "TIRE_INFO_CANNOT_BE_NULL")
    private String tireInfo;

    private Long trimId;
}
