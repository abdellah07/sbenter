package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Recif;

import java.util.List;

public class Rectangle extends Shape {
    private double width;
    private double height;
    private double orientation;

    /**
     * Constructor.
     *@param width value of the rectangle's width
     * @param height value of the rectangle's height
     * @param orientation the orientation of the rectangle
     */

    @JsonCreator
    public Rectangle(@JsonProperty("width") double width
            ,@JsonProperty("height") double height
            ,@JsonProperty("orientation") double orientation) {
        super("rectangle");
        this.width = width;
        this.height = height;
        this.orientation = orientation;
    }

    /**
     * @return the width of the rectangle
     */

    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle
     */

    public double getHeight() {
        return height;
    }

    /**
     * @return the orientation of the rectangle
     */

    public double getOrientation() {
        return orientation;
    }

    /**
     *
     * @param width width to set
     */

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     *
     * @param height height to set
     */

    public void setHeight(double height) {
        this.height = height;
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
        return  super.toString()+"\n\t\twidth=" + width +
                ",\n\t\theight=" + height +
                ",\n\t\torientation=" + orientation+
                "\n\t\t}";
    }
}
