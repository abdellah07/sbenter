package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Circle extends Shape {
    private double radius;

    /**
     * Constructor.
     *
     * @param radius the radius of the circle
     *
     */

    @JsonCreator
    public Circle(@JsonProperty("radius") double radius) {
        super("circle");
        this.radius = radius;
    }

    /**
     * @return the value of the radius
     */

    public double getRadius() {
        return radius;
    }

    /**
     *
     * @param radius radius to set
     */

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return  super.toString()+"\n\tradius=" + radius;
    }
}
