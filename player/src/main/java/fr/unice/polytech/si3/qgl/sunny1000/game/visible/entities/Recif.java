package fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Shape;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;

public class Recif extends Entities {
    @JsonCreator
    public Recif(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape) {
        super("reef", position, shape);
    }

    public Point toPoint(){
        return this.getPosition().toPoint();
    }
}
