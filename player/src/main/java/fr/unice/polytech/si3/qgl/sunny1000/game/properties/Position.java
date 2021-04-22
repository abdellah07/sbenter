package fr.unice.polytech.si3.qgl.sunny1000.game.properties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;

public class  Position{
    private double x;
    private double y;
    private double orientation;

    @JsonCreator
    public Position(@JsonProperty("x") double x,@JsonProperty("y") double y,@JsonProperty("orientation") double orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    /**
     * @return the value of x
     */

    public double getX() {
        return x;
    }

    /**
     *
     * @param x x to set
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the value of y
     */

    public double getY() {
        return y;
    }

    /**
     *
     * @param y y to set
     */

    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the value of orientation
     */

    public double getOrientation() {
        return orientation;
    }

    /**
     *
     * @param orientation orientation to set
     */

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }

    public Point toPoint(){
        return new Point(x,y);
    }

}
