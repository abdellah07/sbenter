package fr.unice.polytech.si3.qgl.sunny1000.game;

import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Entity;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Oar;

import java.util.ArrayList;
import java.util.List;

public class Objective {
    private Sailor sailor;
    private Entity entity;
    private boolean done;

    public Objective(Sailor sailor, Entity entity){
        this.sailor=sailor;
        this.entity=entity;
        done=false;
    }

    public Sailor getSailor() {
        return sailor;
    }

    public void setSailor(Sailor sailor) {
        this.sailor = sailor;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Objective{" +
                "sailor=" + sailor +
                ", entity=" + entity +
                ", done=" + done +
                "}\n";
    }

    public static List<Sailor> usedSailors(List<Objective> objectives){
        List<Sailor> sailors=new ArrayList<>();
        for (Objective o:
             objectives) {
            sailors.add(o.getSailor());
        }
        return sailors;
    }


}
