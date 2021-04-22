package fr.unice.polytech.si3.qgl.sunny1000.game.actions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;

import java.util.ArrayList;

public class OarAction extends Actions{

    /**
     * Constructor.
     *
     * @param sailorId the id of the sailor who will execute the action
     *
     */

    @JsonCreator
    public OarAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId,"OAR");
    }

    public static ArrayList<OarAction> sailorsOar(Sailor s[]){
        ArrayList<OarAction> oars=new ArrayList<>();
        for (int i = 0;i<s.length;i++){
            oars.add(new OarAction(s[i].getId()));
        }
        return oars;
    }

    @Override
    public String toString() {
        return super.toString()+"}";
    }
}
