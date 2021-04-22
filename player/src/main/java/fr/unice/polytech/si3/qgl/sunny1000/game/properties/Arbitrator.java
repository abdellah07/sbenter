package fr.unice.polytech.si3.qgl.sunny1000.game.properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.sunny1000.Captain;
import fr.unice.polytech.si3.qgl.sunny1000.Cockpit;
import fr.unice.polytech.si3.qgl.sunny1000.game.InitGame;
import fr.unice.polytech.si3.qgl.sunny1000.game.NextRound;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.RegattaGoal;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Circle;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Rectangle;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Entities;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Recif;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage.Wind;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arbitrator {
    InitGame iGame;
    NextRound nextRound;
    Captain captain;
    List<Entities> entities;


    public Arbitrator(String initGame, Wind wind) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            iGame = objectMapper.readValue(initGame, InitGame.class);
            captain=new Captain(iGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nGame="{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":10184.080964386741,\"y\":2094.6958871016236,\"orientation\":-0.18402821441536718},\"name\":\"sunny1000\",\"deck\":{\"width\":4,\"length\":9},\"entities\":[{\"x\":1,\"y\":3,\"type\":\"oar\"},{\"x\":2,\"y\":3,\"type\":\"oar\"},{\"x\":3,\"y\":3,\"type\":\"oar\"},{\"x\":4,\"y\":3,\"type\":\"oar\"},{\"x\":5,\"y\":3,\"type\":\"oar\"},{\"x\":6,\"y\":3,\"type\":\"oar\"},{\"x\":7,\"y\":3,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"rudder\"},{\"x\":4,\"y\":1,\"type\":\"sail\",\"openned\":true}],\"life\":800,\"shape\":{\"type\":\"rectangle\",\"width\":4.0,\"height\":9.0,\"orientation\":0.0}},\"visibleEntities\":[" +
                "{ \"position\": { \"x\": 10690.10416666666, \"y\": 2421.8750000000005, \"orientation\":1.0122909661567112 }, \"type\": \"reef\", \"shape\": { \"type\": \"rectangle\", \"width\": 500, \"height\": \"1200\" } }, { \"position\": { \"x\": 13229.166666666666, \"y\": 2447.9166666666647, \"orientation\": 1.0122909661567112 }, \"type\": \"reef\", \"shape\": { \"type\": \"polygon\", \"vertices\": [ { \"x\": \"-300\", \"y\": \"-300\" }, { \"x\": \"-300\", \"y\": 0 }, { \"x\": 0, \"y\": \"300\" }, { \"x\": \"300\", \"y\": \"300\" }, { \"x\": \"500\", \"y\": \"-200\" } ] } }, { \"position\": { \"x\": 7486.979166666666, \"y\": 2460.937499999997, \"orientation\": 0 }, \"type\": \"reef\", \"shape\": { \"type\": \"circle\", \"radius\": \"350\" } }, { \"position\": { \"x\": 4049.4791666666665, \"y\": 2486.9791666666647, \"orientation\": 0 }, \"type\": \"reef\", \"shape\": { \"type\": \"rectangle\", \"width\": 500, \"height\": 500 } }" +
                "],\"wind\":{\"orientation\":0.0,\"strength\":0.0}}\n";
        NextRound n = null;
        try {
            n = objectMapper.readValue(nGame, NextRound.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entities= Arrays.asList(n.getVisibleEntities());
        nextRound=new NextRound(iGame.getShip(),entities.toArray(new Entities[]{}),wind);
    }
    List<Entities> nearEntities(){
        List<Entities> visibleEntities=new ArrayList<>();
        for (Entities entity:entities) {
            if(Util.calculateDistance(nextRound.getShip().getPosition().toPoint(),entity.getPosition().toPoint())<=5000){
                visibleEntities.add(entity);
            }
        }
        return visibleEntities;
    }

    public void startGame(){
        String reality="";

        Captain captain=new Captain(iGame);
        String x="";
        String y="";

        do {
        //for (int i = 0; i < 2; i++) {
            nextRound.setVisibleEntities(nearEntities().toArray(new Entities[]{}));
            captain.nextRound(nextRound);

            captain.choseActions();
            Point shipPosition = captain.getShip().getPosition().toPoint();
            int n = 100;
            double oarSpeed = Util.calculateOarSpeed(captain.getActiveOar(), captain.getShip().getOars().size(), captain.getShip().getPosition().getOrientation());
            double neededOrientation = captain.getInstantRotation();
            double shipOrientation = nextRound.getShip().getPosition().getOrientation() + nextRound.getShip().getShape().getOrientation();
            CalculatePath calculatePath = new CalculatePath(shipPosition, shipOrientation, n, captain.getWind(), captain.getActiveSail(), neededOrientation, captain.getShip(), oarSpeed);
            List<Point> p = calculatePath.generateNextPos();
            /*if (captain.getiCheckPoint()<((RegattaGoal)captain.getGOAL()).getCheckpoints().length){
            x += p.getX() + ", ";
            y += p.getY() + ", ";}
            else {
                x += p.getX();
                y += p.getY();
            }*/
            x += Util.stringList(p, true);
            y += Util.stringList(p, false);
            shipOrientation = calculatePath.getTotalRotation();
            nextRound.getShip().getPosition().setX(p.get(p.size() - 1).getX());
            nextRound.getShip().getPosition().setY(p.get(p.size() - 1).getY());
            nextRound.getShip().getPosition().setOrientation(shipOrientation);
        //}

        }while (captain.getiCheckPoint()<((RegattaGoal)captain.getGOAL()).getCheckpoints().length);
        //System.out.println("startlog");
        //System.out.println(captain.getX());
        //System.out.println();
        //System.out.println(captain.getY());
        //System.out.println("endLog");
        System.out.println(x);
        System.out.println();
        System.out.println(y);
        String json="{\"checkpoints\":";
        try {
            json+=Util.getObjectMapper().writeValueAsString(((RegattaGoal)iGame.getGoal()).getCheckpoints());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json+"}");

        /*json="{\"reef\":";
        try {
            json+=Util.getObjectMapper().writeValueAsString(entities);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json+"}");*/
    }
}

