package fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;

import javax.xml.parsers.SAXParser;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY ,property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= Oar.class, name = "oar"),
        @JsonSubTypes.Type(value= Rudder.class, name = "rudder"),
        @JsonSubTypes.Type(value= Sail.class, name = "sail"),
        @JsonSubTypes.Type(value= Vigie.class, name = "watch")
})


public abstract class Entity {
    private String type;
    private int x;
    private int y;

    /**
     * Constructor.
     *@param type Sting of the type of the entity
     *@param x x-axis value of the entity's position
     *@param y y-axis value of the entity's position
     */

    public Entity(@JsonProperty("type") String type, @JsonProperty("x") int x,
                  @JsonProperty("y") int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the type of entity
     */

    public String getType() {
        return type;
    }

    /**
     * @return the value of x
     */

    public int getX() {
        return x;
    }

    /**
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param type type to set
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param x x to set
     */

    public void setX(int x) {
        this.x = x;
    }


    /**
     *
     * @param y y to set
     */

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Entities{" +
                "type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public Point getPoint(){
        return new Point(this.x,this.y);
    }


    public Sailor findNearestSailor(List<Sailor> sailors){
        if(sailors.size()<=0) {
            return null;
        }
        double min = Util.calculateDistance(sailors.get(0).getPoint(), getPoint());

        Sailor nearestSailor = sailors.get(0);
        for (int i = 1; i < sailors.size(); i++) {
            if (Util.calculateDistance(sailors.get(i).getPoint(), getPoint()) < min) {
                nearestSailor = sailors.get(i);
                min = Util.calculateDistance(sailors.get(i).getPoint(), getPoint());
            }
        }
        return nearestSailor;
    }
}
