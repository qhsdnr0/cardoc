package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Trim;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "ACCOUNT_CANNOT_BE_NULL")
    private String account;

    @NotEmpty(message = "PASSWORD_CANNOT_BE_NULL")
    private String password;
}
