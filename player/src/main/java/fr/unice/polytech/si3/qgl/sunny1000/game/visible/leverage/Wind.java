package fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
    private double orientation;
    private double strength;

    @JsonCreator
    public Wind(@JsonProperty("orientation") double orientation,@JsonProperty("strength") double strength) {
        this.orientation = orientation;
        this.strength = strength;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "orientation=" + orientation +
                ", strength=" + strength +
                '}';
    }
}
