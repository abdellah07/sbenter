package fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Shape;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY ,property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= Courant.class, name = "stream"),
        @JsonSubTypes.Type(value= Bateau.class, name = "ship"),
        @JsonSubTypes.Type(value= Recif.class, name = "reef"),
})
public class Entities {
    private String type;
    private Position position;
    private Shape shape;

    /**
     * Constructor.
     *@param type the type of the entity
     *@param position the position of the entity
     *@param shape the shape of the entity
     */

    @JsonCreator
    public Entities(@JsonProperty("type") String type,
                    @JsonProperty("position") Position position,
                    @JsonProperty("shape") Shape shape) {
        this.type = type;
        this.position = position;
        this.shape = shape;
    }

    /**
     * @return the type of entity
     */

    public String getType() {
        return type;
    }

    /**
     * @param type type to set
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the position of the entity
     */

    public Position getPosition() {
        return position;
    }

    /**
     * @param position position to set
     */

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return the shape of the entity
     */

    public Shape getShape() {
        return shape;
    }

    /**
     * @param shape shape to set
     */

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Entitie{" +
                "type='" + type + '\'' +
                ", position=" + position +
                ", shape=" + shape +
                '}';
    }
}
