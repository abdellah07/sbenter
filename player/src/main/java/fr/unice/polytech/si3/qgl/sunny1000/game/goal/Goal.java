package fr.unice.polytech.si3.qgl.sunny1000.game.goal;



import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.RegattaGoal;


import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY ,property = "mode")
@JsonSubTypes({
        @JsonSubTypes.Type(value= RegattaGoal.class, name = "REGATTA"),
        @JsonSubTypes.Type(value= BattleGoal.class, name = "BATTLE"),
})

public abstract class Goal {

    private String mode;

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Constructor.
     *@param mode Sting value of the game mode
     */

    public Goal(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "\n\t\tmode='" + mode + '\'' +
                "\n\t}";
    }
}
