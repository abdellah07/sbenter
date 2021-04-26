package fr.unice.polytech.si3.qgl.sunny1000.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.Checkpoint;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Entities;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage.Wind;

import java.util.Arrays;
import java.util.List;

public class NextRound {
    private Ship ship;
    private Entities visibleEntities[];
    private Wind wind;

    /**
     * Constructor.
     * @param ship the ship used in the round
     * @param visibleEntities the entities included in the ship
     * @param wind wind present in the round
     */


    @JsonCreator
    public NextRound(@JsonProperty("ship") Ship ship,
                     @JsonProperty("visibleEntities") Entities[] visibleEntities,
                     @JsonProperty("wind") Wind wind) {
        this.ship = ship;
        this.visibleEntities = visibleEntities;
        this.wind = wind;
    }

    /**
     * @return the ship used in the round
     */

    public Ship getShip() {
        return ship;
    }

    /**
     * @param ship ship to set
     */

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * @return the visible entities
     */

    public Entities[] getVisibleEntities() {
        return visibleEntities;
    }

    /**
     * @param visibleEntities visibleEntities to set
     */

    public void setVisibleEntities(Entities[] visibleEntities) {
        this.visibleEntities = visibleEntities;
    }

    /**
     * @return the wind in the round
     */

    public Wind getWind() {
        return wind;
    }

    /**
     * @param wind wind to set
     */

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Round{" +
                "ship=" + ship +
                ", visibleEntities=" + Arrays.toString(visibleEntities) +
                ", wind=" + wind +
                '}';
    }


}
