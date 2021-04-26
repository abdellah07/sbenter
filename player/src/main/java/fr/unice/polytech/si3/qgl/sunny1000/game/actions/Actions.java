package fr.unice.polytech.si3.qgl.sunny1000.game.actions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY ,property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= OarAction.class, name = "OAR"),
        @JsonSubTypes.Type(value= LiftSail.class, name = "LIFT_SAIL"),
        @JsonSubTypes.Type(value= LowerSail.class, name = "LOWER_SAIL"),
        @JsonSubTypes.Type(value= OarAction.class, name = "TURN")
})
public abstract class Actions {
    private int sailorId;
    private String type;

    /**
     * Constructor.
     *
     * @param sailorId the id of the sailor who will execute the action
     * @param type the type of the action
     */

    @JsonCreator
    public Actions(@JsonProperty("sailorId") int sailorId, @JsonProperty("type") String type) {
        this.sailorId = sailorId;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "sailorId=" + sailorId +
                ", type='" + type + '\'';
    }


    public String getType() {
        return type;
    }

    /**
     *
     * @param type type to set (in cents)
     */

    public void setType(String type) {
        this.type = type;
    }
}
