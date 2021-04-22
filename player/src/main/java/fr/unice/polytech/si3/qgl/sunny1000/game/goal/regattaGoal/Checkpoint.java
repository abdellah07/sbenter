package fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Shape;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;


public class Checkpoint {
    private Position position;
    private Shape shape;
    private boolean ours;
    @JsonCreator
    public Checkpoint(@JsonProperty("position") Position position,@JsonProperty("shape") Shape shape) {
        this.position = position;
        this.shape = shape;
        ours=false;
    }

    public void setOursTrue() {
        this.ours = true;
    }

    public boolean isOurs() {
        return ours;
    }

    /**
     * @return position of the checkpoint
     */
    public Position getPosition() {
        return position;
    }

    /**
     *
     * @param position position to set
     */

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return shape of the checkpoint
     */

    public Shape getShape() {
        return shape;
    }

    /**
     *
     * @param shape to set
     */

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Checkpoint{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

    public Point toPoint(){
        return  new Point(position.getX(), position.getY());
    }
}
