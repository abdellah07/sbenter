package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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

    public Point[] getVerticesPosition(Point position){
        ArrayList<Point> pts=new ArrayList<>();
        for (Point pt:
                vertices) {
            Point k=new Point(pt.getX()+position.getX(),pt.getY()+position.getY());
            pts.add(k);
        }
        return pts.toArray(new Point[]{});
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


    @Override
    public String toString() {
        return  super.toString()+"\n\torientation=" + orientation +
                ", \n\tvertics=" + Arrays.toString(vertices);
    }
}
