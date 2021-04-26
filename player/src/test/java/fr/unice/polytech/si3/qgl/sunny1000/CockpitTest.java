package fr.unice.polytech.si3.qgl.sunny1000;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.RegattaGoal;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Arbitrator;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.CalculatePath;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Circle;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Recif;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage.Wind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CockpitTest {

    Cockpit cockpit;

    @BeforeEach
    void setUp() {
        this.cockpit = new Cockpit();
    }

   @Test
    void testArbitre(){
        String initGame="{\"goal\":{\"mode\":\"REGATTA\",\"checkpoints\":[{\"position\":{\"x\":5533.854166666661,\"y\":2499.9999999999973,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":150.0}},{\"position\":{\"x\":9088.541666666666,\"y\":2239.5833333333335,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":850.0}},{\"position\":{\"x\":11679.687499999985,\"y\":2460.937499999999,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":150.0}},{\"position\":{\"x\":14973.958333333332,\"y\":2460.937499999996,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":150.0}}]},\"ship\":{\"type\":\"ship\",\"position\":{\"x\":2460.9375,\"y\":2513.020833333333,\"orientation\":0.0},\"name\":\"sunny1000\",\"deck\":{\"width\":4,\"length\":9},\"entities\":[{\"x\":1,\"y\":3,\"type\":\"oar\"},{\"x\":2,\"y\":3,\"type\":\"oar\"},{\"x\":3,\"y\":3,\"type\":\"oar\"},{\"x\":4,\"y\":3,\"type\":\"oar\"},{\"x\":5,\"y\":3,\"type\":\"oar\"},{\"x\":6,\"y\":3,\"type\":\"oar\"},{\"x\":7,\"y\":3,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"rudder\"},{\"x\":4,\"y\":1,\"type\":\"sail\",\"openned\":false}],\"life\":1800,\"shape\":{\"type\":\"rectangle\",\"width\":4.0,\"height\":9.0,\"orientation\":0.0}},\"sailors\":[{\"x\":0,\"y\":0,\"id\":0,\"name\":\"Luffy Pouce\"},{\"x\":0,\"y\":1,\"id\":1,\"name\":\"Luffy Teach\"},{\"x\":0,\"y\":2,\"id\":2,\"name\":\"Jack Teach\"},{\"x\":0,\"y\":3,\"id\":3,\"name\":\"Jack Teach\"},{\"x\":1,\"y\":0,\"id\":4,\"name\":\"Tom Teach\"},{\"x\":1,\"y\":1,\"id\":5,\"name\":\"Jack Teach\"},{\"x\":1,\"y\":2,\"id\":6,\"name\":\"Edward Pouce\"},{\"x\":1,\"y\":3,\"id\":7,\"name\":\"Edward Pouce\"},{\"x\":2,\"y\":0,\"id\":8,\"name\":\"Jack Teach\"},{\"x\":2,\"y\":1,\"id\":9,\"name\":\"Tom Teach\"},{\"x\":2,\"y\":2,\"id\":10,\"name\":\"Luffy Teach\"},{\"x\":2,\"y\":3,\"id\":11,\"name\":\"Luffy Pouce\"},{\"x\":3,\"y\":0,\"id\":12,\"name\":\"Luffy Pouce\"},{\"x\":3,\"y\":1,\"id\":13,\"name\":\"Luffy Pouce\"}],\"shipCount\":1}\n";
        Arbitrator a=new Arbitrator(initGame,new Wind(50,150));
        a.startGame();
    }

    /*@Test
    void test(){
        ArrayList<Recif> r = new ArrayList<>();
        r.add(new Recif(new Position(0,0,0),new Circle(50)));
        String json="";
        try {
            json=Util.getObjectMapper().writeValueAsString(r);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("{\"reef\":" + json + "}");
        //log.add(captain.getShip().getPosition().getX()+" "+captain.getShip().getPosition().getY()+" "+captain.getShip().getPosition().getOrientation());

    }*/
}