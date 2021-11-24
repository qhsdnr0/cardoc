package Cardoc.cardoc.models;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class TireInfo {

    private int flat;
    private int wheelSize;
    private int width;

    protected TireInfo() {}

    public TireInfo(int flat, int wheelSize, int width) {
        this.width = width;
        this.flat = flat;
        this.wheelSize = wheelSize;
    }
}
