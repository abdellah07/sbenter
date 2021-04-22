package fr.unice.polytech.si3.qgl.sunny1000.game.goal;



import com.fasterxml.jackson.annotation.JsonCreator;

class BattleGoal extends Goal {

    /**
     * Constructor.
     */

    @JsonCreator
    public BattleGoal() {
        super("BATTLE");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
