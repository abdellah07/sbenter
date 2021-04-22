package fr.unice.polytech.si3.qgl.sunny1000.game.Ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.*;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Rectangle;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Ship {
    private String type;
    private int life;
    private Position position;
    private String name;
    private Deck deck;
    private Entity[] entities;
    private Rectangle shape;

    /**
     * Constructor.
     *@param type String value of the ship's type
     *@param life number of ship's life
     *@param position x y coordinates and orientation of the ship
     *@param name the ship's name
     *@param deck the ship's deck
     *@param entities list of entities included in the ship
     *@param shape the ship's shape
     */

    @JsonCreator
    public Ship(@JsonProperty("type") String type, @JsonProperty("life") int life,
                @JsonProperty("position")Position position,
                @JsonProperty("name")String name, @JsonProperty("deck")Deck deck,
                @JsonProperty("entities") Entity[] entities, @JsonProperty("shape") Rectangle shape) {
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }

    public Ship(){
        position=new Position(0,0,0);
    }

    @Override
    public Ship clone()  {
        return new Ship(type,life,position,name,deck,entities,shape);
    }

    public int getY_RIGHT() {
        return getLeftOars(1).get(0).getY();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "\n\t\ttype='" + type + '\'' +
                ",\n\t\tlife=" + life +
                ",\n\t\tposition=" + position +
                ",\n\t\tname='" + name + '\'' +
                ",\n\t\tdeck=" + deck +
                ",\n\t\tentities=" + Arrays.toString(entities) +
                ",\n\t\tshape=" + shape +
                "\n}";
    }

    /**
     * this methode return the left oars of the ship where y is different than 0
     * @return
     */
    public List<Oar> getLeftOars(int number){
        List<Oar> leftOars=new ArrayList<>();
        List<Oar> oars = getOars();
        for (Oar oar:
                oars) {
            if(oar.getY()!=0 && number>0) {
                leftOars.add(oar);
                number--;
            }
        }
        return leftOars;
    }

    /**
     * this methode return the right oars of the ship where y is equal to 0
     * @return
     */
    public List<Oar> getRightOars(int number){
        List<Oar> leftOars=new ArrayList<>();
        List<Oar> oars = getOars();
        for (Oar oar:
                oars) {
            if(oar.getY()==0 && number>0) {
                leftOars.add(oar);
                number--;
            }
        }
        return leftOars;
    }

    public ArrayList<Oar> getOars(){
        ArrayList<Oar> rames=new ArrayList<>();
        for (Entity entitie : this.entities) {
            if(entitie instanceof Oar){
                rames.add((Oar) entitie);
            }
        }
        return rames;
    }

    public Rudder getRudder(){

        for (Entity entity : this.entities) {
            if(entity instanceof Rudder){
                return (Rudder) entity;
            }
        }
        return null;
    }

    public Vigie getWatch(){

        for (Entity entity : this.entities) {
            if(entity instanceof Vigie){
                return (Vigie) entity;
            }
        }
        return null;
    }

    public Sail getSail(){
        for (Entity entity : this.entities){
            if(entity instanceof  Sail){
                return (Sail) entity;
            }
        }
        return null;
    }



}
