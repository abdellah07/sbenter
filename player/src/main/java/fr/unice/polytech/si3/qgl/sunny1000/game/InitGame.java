package fr.unice.polytech.si3.qgl.sunny1000.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.Goal;

import java.util.Arrays;

public class InitGame {
    private Goal goal;
    private Ship ship;
    private Sailor sailors[];
    private int shipCount;

    /**
     * Constructor.
     *@param goal game's goal
     *@param ship the ship used in the game
     *@param sailors list of sailors present in the ship
     */

    @JsonCreator
    public InitGame(@JsonProperty("goal") Goal goal,
                @JsonProperty("ship") Ship ship,
                @JsonProperty("sailors") Sailor[] sailors,
                @JsonProperty("shipCount") int shipCount) {
        this.goal = goal;
        this.ship = ship;
        this.sailors = sailors;
        this.shipCount=shipCount;
    }

    /**
     * @return the goal of the game
     */

    public Goal getGoal() {
        return goal;
    }

    /**
     * @param goal goal to set
     */

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * @return the ship of the game
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
     * @return the sailors of the game
     */

    public Sailor[] getSailors() {
        return sailors;
    }

    /**
     * @param sailors sailors to set
     */

    public void setSailors(Sailor[] sailors) {
        this.sailors = sailors;
    }

    @Override
    public String toString() {
        return "\n\tgoal=" + goal +
                ",\n\tship=" + ship +
                ",\n\tsailors=" + Arrays.toString(sailors)
                ;
    }



}
