package Cardoc.cardoc.util;

import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.TireInfo;
import org.springframework.stereotype.Component;


@Component
public class Validation {

    public static TireInfo makeTireInfo(String strTireInfo) {
        int width = Integer.parseInt(strTireInfo.split("/")[0]);
        int flat = Integer.parseInt((strTireInfo.split("/")[1].split("R")[0]));
        int wheelSize = Integer.parseInt((strTireInfo.split("/")[1].split("R")[1]));

        return new TireInfo(width, flat, wheelSize);
    }

    public static void validateUserAccount(String account) {
        String pattern = "^[a-zA-Z][a-zA-Z0-9]{5,14}$";
        if (!account.matches(pattern)) {
            throw new BadRequestException("INVALID_ACCOUNT");
        }
    }

    public static void validateUserPassword(String password) {
        String pattern = ".*([!@#$%^&*].*)";
        if (!password.matches(pattern) || password.length() < 8 || password.length() > 18) {
            throw new BadRequestException("INVALID_PASSWORD");
        }
    }
}
