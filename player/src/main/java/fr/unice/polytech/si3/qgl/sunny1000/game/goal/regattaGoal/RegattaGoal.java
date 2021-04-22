package fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.Goal;

import java.util.Arrays;

public class RegattaGoal extends Goal {
    private Checkpoint checkpoints[];

    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Checkpoint[] checkpoints) {
        this.checkpoints = checkpoints;
    }

    /**
     * Constructor.
     *@param checkpoints a list of checkpoints in the Regatta game mode
     */

    @JsonCreator
    public RegattaGoal(
                       @JsonProperty("checkpoints") Checkpoint[] checkpoints) {
        super("REGATTA");
        this.checkpoints = checkpoints;
    }

    @Override
    public String toString() {
        return "RegattaGoal{" +
                "checkpoints=" + Arrays.toString(checkpoints) +
                '}';
    }
}
