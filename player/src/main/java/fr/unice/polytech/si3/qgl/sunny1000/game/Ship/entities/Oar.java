package fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Entity;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;

import java.util.List;

public class Oar extends Entity {

    /**
     * Constructor.
     *@param type Sting value of the type of the entity
     *@param x x-axis value of the paddle's position
     *@param y y-axis value of the paddle's position
     */

    public Oar(@JsonProperty("type") String type, @JsonProperty("x") int x,
               @JsonProperty("y") int y) {
        super("oar",x,y);
    }

}
