package fr.unice.polytech.si3.qgl.sunny1000;

import fr.unice.polytech.si3.qgl.sunny1000.game.InitGame;
import fr.unice.polytech.si3.qgl.sunny1000.game.NextRound;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Deck;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Rudder;
import fr.unice.polytech.si3.qgl.sunny1000.game.actions.Actions;
import fr.unice.polytech.si3.qgl.sunny1000.game.actions.OarAction;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.Goal;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.Checkpoint;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.RegattaGoal;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Circle;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Rectangle;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaptainTest {
    Captain captain;
    Goal goal;
    @BeforeEach
    void setUp(){
        String initGame = "{ \"goal\": { \"mode\": \"REGATTA\", \"checkpoints\": [ { \"position\": { \"x\": 2279.6317218053687, \"y\": 0, \"orientation\": 0 }, \"shape\": { \"type\": \"circle\", \"radius\": 100 } } ] }, \"ship\": { \"type\": \"ship\", \"position\": { \"x\": 0, \"y\": 0, \"orientation\": 1.5882496193148399 }, \"name\": \"sunny1000\", \"deck\": { \"width\": 3, \"length\": 4 }, \"entities\": [ { \"x\": 0, \"y\": 0, \"type\": \"oar\" }, { \"x\": 0, \"y\": 2, \"type\": \"oar\" }, { \"x\": 1, \"y\": 0, \"type\": \"oar\" }, { \"x\": 1, \"y\": 2, \"type\": \"oar\" }, { \"x\": 2, \"y\": 0, \"type\": \"oar\" }, { \"x\": 2, \"y\": 2, \"type\": \"oar\" }, { \"x\": 3, \"y\": 1, \"type\": \"rudder\" } ], \"life\": 300, \"shape\": { \"type\": \"rectangle\", \"width\": 3, \"height\": 4, \"orientation\": 0 } }, \"sailors\": [ { \"x\": 0, \"y\": 0, \"id\": 0, \"name\": \"Jack Teach\" }, { \"x\": 0, \"y\": 1, \"id\": 1, \"name\": \"Luffy Teach\" }, { \"x\": 0, \"y\": 2, \"id\": 2, \"name\": \"Tom Pouce\" }, { \"x\": 1, \"y\": 0, \"id\": 3, \"name\": \"Edward Teach\" }, { \"x\": 1, \"y\": 2, \"id\": 4, \"name\": \"Tom Pouce\" }, { \"x\": 2, \"y\": 0, \"id\": 10, \"name\": \"zoro tompouce\" }, { \"x\": 2, \"y\": 1, \"id\": 11, \"name\": \"eren teach\" } ], \"shipCount\": 1 }";
        InitGame iGame;
        NextRound nRound;
        try {
            iGame = Util.getObjectMapper().readValue(initGame, InitGame.class);
            captain=new Captain(iGame);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String nextRound="{ \"ship\": { \"type\": \"ship\", \"position\": { \"x\": 0, \"y\": 0, \"orientation\": 0 }, \"name\": \"sunny1000\", \"deck\": { \"width\": 3, \"length\": 4 }, \"entities\": [ { \"x\": 0, \"y\": 0, \"type\": \"oar\" }, { \"x\": 0, \"y\": 2, \"type\": \"oar\" }, { \"x\": 1, \"y\": 0, \"type\": \"oar\" }, { \"x\": 1, \"y\": 2, \"type\": \"oar\" }, { \"x\": 2, \"y\": 0, \"type\": \"oar\" }, { \"x\": 2, \"y\": 2, \"type\": \"oar\" }, { \"x\": 3, \"y\": 1, \"type\": \"rudder\" } ], \"life\": 300, \"shape\": { \"type\": \"rectangle\", \"width\": 3, \"height\": 4, \"orientation\": 0 } }, \"visibleEntities\": [], \"wind\": { \"orientation\": 0, \"strength\": 0 } }";
        try {
            nRound = Util.getObjectMapper().readValue(nextRound, NextRound.class);
            captain.nextRound(nRound);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAngleShipOrientation0(){
        double shipOrientation=Math.toRadians(0);
        captain.getShip().getPosition().setOrientation(shipOrientation);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        //change the checkpoint location
        Position p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);

        //change the checkpoint location
        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);
    }

    @Test
    public void testAngleShipOrnamentation45(){
        double shipOrientation=Math.toRadians(45);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testAngleShipOrnamentation90(){
        double shipOrientation=Math.toRadians(90);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testAngleShipOrnamentation135(){
        double shipOrientation=Math.toRadians(135);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testAngleShipOrnamentation180(){
        double shipOrientation=Math.toRadians(180);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testAngleShipOrnamentation225(){
        double shipOrientation=Math.toRadians(-135);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testAngleShipOrnamentation270(){
        double shipOrientation=Math.toRadians(-90);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testAngleShipOrnamentation315(){
        double shipOrientation=Math.toRadians(-45);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        p=new Position(0,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-45);

        //change the checkpoint location
        p=new Position(-2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-90);

        //change the checkpoint location
        p=new Position(-2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),-135);

        //change the checkpoint location
        p=new Position(-2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),180);

        //change the checkpoint location
        p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);

        //change the checkpoint location
        p=new Position(2000,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),90);

        //change the checkpoint location
        p=new Position(2000,0,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),45);
    }

    @Test
    public void testLeftSailors(){
        List<Sailor> sailors=new ArrayList<>();
        sailors=captain.findSailorsOarLeft();
        assertEquals(sailors.size(),0);
        captain.choseActions();
        sailors=captain.findSailorsOarLeft();
        assertNotEquals(sailors.size(),0);

        assertEquals(sailors.get(0).getY(),0);
        assertEquals(sailors.get(sailors.size()-1).getY(),0);
    }

    @Test
    public void testRightSailors(){
        List<Sailor> sailors=new ArrayList<>();
        sailors=captain.findSailorsOarRight();
        assertEquals(sailors.size(),0);
        captain.choseActions();
        sailors=captain.findSailorsOarRight();
        assertNotEquals(sailors.size(),0);

        assertEquals(sailors.get(0).getY(),captain.getShip().getY_RIGHT());
        assertEquals(sailors.get(sailors.size()-1).getY(),captain.getShip().getY_RIGHT());
    }


    @Test
    public void actionsForwardTest(){
        double shipOrientation=Math.toRadians(-45);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        Position p=new Position(2000,-2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),0);

        List<Sailor> sailors = new ArrayList<>(captain.findSailorsOarLeft());
        sailors.addAll(captain.findSailorsOarRight());

        List<Actions> expectedActions = new ArrayList<>();
        for (Sailor sailor:
             sailors) {
            expectedActions.add(new OarAction(sailor.getId()));
        }

        List<Actions> actualActions = captain.choseActions();
        System.out.println(actualActions);
        for (int i = 0; i < captain.choseActions().size(); i++) {
            expectedActions.contains(actualActions.get(i));
        }
    }

    @Test
    public void test(){
        double shipOrientation=Math.toRadians(-45);
        captain.getShip().getPosition().setOrientation(shipOrientation);

        //change the checkpoint location
        Position p=new Position(0,2000,0);
        ((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].setPosition(p);
        captain.choseActions();
        assertEquals(captain.getRotationAngle(),135);
        System.out.println(captain.choseActions());
    }

    @Test
    public void createVirtualCheckPointTest(){
        Checkpoint checkpoint1 = new Checkpoint(new Position(10,20,0),new Circle(20));
        Checkpoint checkpoint2 = new Checkpoint(new Position(100,20,0),new Circle(20));
        Checkpoint checkpoint3 = new Checkpoint(new Position(1000,20,0),new Circle(20));
        Checkpoint checkpoint4 = new Checkpoint(new Position(10,200,0),new Circle(20));
        Checkpoint[] checkpoints = {checkpoint1,checkpoint2,checkpoint3,checkpoint4};
        ((RegattaGoal)captain.getGOAL()).setCheckpoints(checkpoints);
        captain.createVirtualCheckPoint(new Position(100,200,0),20);
        assertEquals(((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].getPosition().getX(),100);
        assertEquals(((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].getPosition().getY(),200);
        assertEquals(((RegattaGoal)captain.getGOAL()).getCheckpoints()[0].getPosition().getOrientation(),0);
    }

    


}
