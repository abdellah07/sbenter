package fr.unice.polytech.si3.qgl.sunny1000.game.Ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Deck {
    private int width;
    private int length;

    /**
     * Constructor.
     *@param width value of deck's width
     *@param length value of deck's length
     */

    @JsonCreator
    public Deck(@JsonProperty("width") int width,@JsonProperty("length") int length) {
        this.width = width;
        this.length = length;
    }


    @Override
    public String toString() {
        return "Deck{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }

}
