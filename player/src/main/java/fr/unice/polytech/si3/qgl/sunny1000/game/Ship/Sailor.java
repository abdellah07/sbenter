package fr.unice.polytech.si3.qgl.sunny1000.game.Ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Oar;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;

import java.util.List;
import java.util.Objects;

public class Sailor {
    private int x;
    private int y;
    private int id;
    private String name;

    /**
     * Constructor.
     *@param x x-axis value of the sailor's position
     *@param y y-axis value of the sailor's position
     *@param id value of the sailor's id
     *@param name the sailor's name
     */

    @JsonCreator
    public Sailor(@JsonProperty("x") int x,
                  @JsonProperty("y") int y,
                  @JsonProperty("id") int id,
                  @JsonProperty("name") String name) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Sailor{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailor sailor = (Sailor) o;
        return id == sailor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void moveToNearestOar(){
        Oar findNeaRame;
    }

    public Oar findNearestOar(List<Oar> oars){
        if(oars.size()<=0) {
            return null;
        }
            double min = Util.calculateDistance(oars.get(0).getPoint(), getPoint());

            Oar nearestOar = oars.get(0);
            for (int i = 1; i < oars.size(); i++) {
                if (Util.calculateDistance(oars.get(i).getPoint(), getPoint()) < min) {
                    nearestOar = oars.get(i);
                    min = Util.calculateDistance(oars.get(i).getPoint(), getPoint());
                }
            }

        return nearestOar;
    }

    public Point getPoint(){
        return new Point(this.x,this.y);
    }

}
