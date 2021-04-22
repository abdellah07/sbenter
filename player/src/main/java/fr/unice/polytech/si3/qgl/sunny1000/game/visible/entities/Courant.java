package fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Shape;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;

public class Courant extends Entities {
    private double strength;

    @JsonCreator
    public Courant(@JsonProperty("type") String type,
                   @JsonProperty("position") Position position,
                   @JsonProperty("shape") Shape shape,
                   @JsonProperty("strength") double strength) {
        super("stream", position, shape);
        this.strength=strength;
    }

    /**
     * @return the value of strength
     */

    public double getStrength() {
        return strength;
    }

    /**
     * @param strength strength to set
     */

    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return  super.toString()+"Courant{" +
                "strength=" + strength +
                '}';
    }
}
