package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Polygone extends Shape {
    private double orientation;
    private Point[] vertices;

    /**
     * Constructor.
     *
     * @param orientation the orientation of the polygone
     * @param vertices a list of points that constitute the polygone
     */

    @JsonCreator
    public Polygone(@JsonProperty("orientation") double orientation
            ,@JsonProperty("vertics") Point[] vertices) {
        super("polygone");
        this.orientation = orientation;
        this.vertices = vertices;
    }

    /**
     * @return the value of orientation
     */

    public double getOrientation() {
        return orientation;
    }

    /**
     * @return the list of vertics
     */

    public Point[] getVertices() {
        return vertices;
    }

    /**
     *
     * @param orientation orientation to set
     */

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    /**
     *
     * @param vertices vertics to set
     */

    public void setVertics(Point[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return  super.toString()+"\n\torientation=" + orientation +
                ", \n\tvertics=" + Arrays.toString(vertices);
    }
}
