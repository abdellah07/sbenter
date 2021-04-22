package fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Shape;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;


public class Bateau extends Entities {
    private int life;



    @JsonCreator
    public Bateau(
                   @JsonProperty("position") Position position,
                   @JsonProperty("shape") Shape shape,
                   @JsonProperty("life") int life) {
        super("ship", position, shape);
        this.life=life;
    }


    /**
     * @return the value of life
     */

    public int getLife() {
        return life;
    }

    /**
     * @param life life to set
     */

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public String toString() {
        return  super.toString()+"Bateau{" +
                "life=" + life +
                '}';
    }
}
