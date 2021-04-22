package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY ,property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value=Circle.class, name = "circle"),
        @JsonSubTypes.Type(value=Polygone.class, name = "polygon")
})
public abstract class Shape {
    private String type;

    /**
     * Constructor.
     *@param type the type of the shape
     */

    @JsonCreator
    public Shape(@JsonProperty("type") String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "{\n\t\ttype=" + type;
    }
}
