package fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sail extends Entity{
    private boolean openned;
    /**
     * Constructor.
     *@param type Sting value of the type of the entity
     *@param x x-axis value of the paddle's position
     *@param y y-axis value of the paddle's position
     *@param openned boolean that shows if the sails are opened or not
     */
    @JsonCreator
    public Sail(@JsonProperty("type") String type, @JsonProperty("x") int x,
               @JsonProperty("y") int y, @JsonProperty("openned") boolean openned) {
        super("sail",x,y);
        this.openned = openned;
    }


}
