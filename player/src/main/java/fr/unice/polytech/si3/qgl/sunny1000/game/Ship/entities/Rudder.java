package fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;

import java.util.List;

public class Rudder extends Entity{
    /**
     * Constructor.
     *
     * @param type Sting of the type of the entity
     * @param x    x-axis value of the entity's position
     * @param y    y-axis value of the entity's position
     */

    public Rudder(@JsonProperty("type") String type, @JsonProperty("x") int x,
               @JsonProperty("y") int y) {
        super("rudder",x,y);
    }
}
