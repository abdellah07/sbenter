package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Point{
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x X-axis value of the point
     * @param y Y-axis value of the point
     */
    @JsonCreator
    public Point(@JsonProperty("x") double x,@JsonProperty("y") double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the value of x
     */

    public double getX() {
        return x;
    }

    /**
     * @return the value of y
     */

    public double getY() {
        return y;
    }

    /**
     *
     * @param x x to set
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @param y y to set
     */

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
