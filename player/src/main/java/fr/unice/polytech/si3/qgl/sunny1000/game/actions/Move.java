package fr.unice.polytech.si3.qgl.sunny1000.game.actions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Oar;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;

import java.util.ArrayList;
import java.util.List;

public class Move extends Actions{
    /**
     * ordonnée de la place ou on va deplacer le marin
     */
    private int ydistance;
    /**
     * abscisse de la place ou on va deplacer le marin
     */
    private int xdistance;

    /**
     *
     * @param sailorId
     * @param x abscisse de la place ou on va deplacer le marin
     * @param y ordonnée de la place ou on va deplacer le marin
     */
    @JsonCreator
    public Move(@JsonProperty("sailorId") int sailorId,@JsonProperty("x") int x,@JsonProperty("y") int y) {
        super(sailorId, "MOVING");
        this.xdistance = x;
        this.ydistance = y;

    }



    @Override
    public String toString() {
        return super.toString()+
                ",y=" + ydistance +
                ", x=" + xdistance +
                '}';
    }

    public int getYdistance() {
        return ydistance;
    }

    public void setYdistance(int ydistance) {
        this.ydistance = ydistance;
    }

    public int getXdistance() {
        return xdistance;
    }

    public void setXdistance(int xdistance) {
        this.xdistance = xdistance;
    }

    public static Point validateMove(int xDistance, int yDistance){
        while (Math.abs(xDistance)+ Math.abs(yDistance)>5){
            if(xDistance>0){
                xDistance--;
            }else if(xDistance<0){
                xDistance++;
            }else if(yDistance>0){
                yDistance--;
            }else if(yDistance<0){
                yDistance++;
            }
        }
        return new Point(xDistance,yDistance);
    }
}
