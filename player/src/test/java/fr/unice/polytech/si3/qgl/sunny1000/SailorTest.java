package fr.unice.polytech.si3.qgl.sunny1000;

import fr.unice.polytech.si3.qgl.sunny1000.game.InitGame;
import org.junit.Test;

public class SailorTest {
    @Test
    public void nearestOar(){
        String init="{ \"goal\": { \"mode\": \"REGATTA\", \"checkpoints\": [ { \"position\": { \"x\": -200, \"y\": 1000, \"orientation\": 0 }, \"shape\": { \"type\": \"circle\", \"radius\": 80 } } ] }, \"ship\": { \"type\": \"ship\", \"position\": { \"x\": 0, \"y\": 0, \"orientation\": 0 }, \"name\": \"sunny1000\", \"deck\": { \"width\": 2, \"length\": 4 }, \"entities\": [ { \"x\": 1, \"y\": 0, \"type\": \"oar\" }, { \"x\": 1, \"y\": 1, \"type\": \"oar\" }, { \"x\": 2, \"y\": 0, \"type\": \"oar\" }, { \"x\": 2, \"y\": 1, \"type\": \"oar\" }, { \"x\": 3, \"y\": 0, \"type\": \"oar\" }, { \"x\": 3, \"y\": 1, \"type\": \"oar\" } ], \"life\": 300, \"shape\": { \"type\": \"rectangle\", \"width\": 2, \"height\": 4, \"orientation\": 0 } }, \"sailors\": [ { \"x\": 2, \"y\": 1, \"id\": 0, \"name\": \"Tom Teach\" }, { \"x\": 0, \"y\": 1, \"id\": 1, \"name\": \"Tom Pouce\" }, { \"x\": 1, \"y\": 0, \"id\": 2, \"name\": \"Jack Teach\" }, { \"x\": 1, \"y\": 1, \"id\": 3, \"name\": \"Edward Teach\" } ], \"shipCount\": 1 }";
        Cockpit c=new Cockpit();
        c.initGame(init);
        // InitGame i=c.getInData();
        //System.out.println(c.getInData().getSailors()[0].findNearestOar(i.getShip().getOars()));
    }
}


