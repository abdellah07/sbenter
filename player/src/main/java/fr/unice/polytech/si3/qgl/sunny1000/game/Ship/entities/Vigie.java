package fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vigie extends Entity {

    /**
     * Constructor.
     *@param type Sting value of the type of the entity
     *@param x x-axis value of the paddle's position
     *@param y y-axis value of the paddle's position
     */

    public Vigie(@JsonProperty("type") String type, @JsonProperty("x") int x,
               @JsonProperty("y") int y) {
        super("watch",x,y);
    }
}
