package fr.unice.polytech.si3.qgl.sunny1000.game.Ship;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.sunny1000.Captain;
import fr.unice.polytech.si3.qgl.sunny1000.game.InitGame;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Entity;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Oar;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Ship ship;
    ObjectMapper objectMapper;
    String shipString;
    @BeforeEach
    void setUp() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        shipString = "{\"type\":\"ship\",\"life\":100,\"position\":{\"x\":0,\"y\":0,\"orientation\":0},\"name\":\"Les copaings d'abord!\",\"deck\":{\"width\":3,\"length\":6},\"entities\":[{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"}],\"shape\":{\"type\":\"rectangle\",\"width\":3,\"height\":6,\"orientation\":0}}";
        ship = objectMapper.readValue(shipString, Ship.class);
    }
    @Test
    void test(){
        System.out.println(ship.getPosition().getX());
    }

    @Test
    void getLeftOarsNormalCase(){
        List<Oar> myList = ship.getLeftOars(3);
        assertTrue(myList.size()==3); //testing size
        assertEquals(myList.get(0).getPoint(),new Point(1,2));
        assertEquals(myList.get(1).getPoint(),new Point(3,2));
        assertEquals(myList.get(2).getPoint(),new Point(4,2));
    }

/*   @Test
    void getLeftOarsIfGivenNumberBiggerThanActualOars(){
        List<Oar> myList = ship.getLeftOars(5);
        assertTrue(myList.size()==maxLeftOars); //testing size
    }*/

    @Test
    void getRightOarsNormalCase(){
        List<Oar> myList = ship.getRightOars(3);
        assertTrue(myList.size()==3); //testing size
        assertEquals(myList.get(0).getPoint(),new Point(1,0));
        assertEquals(myList.get(1).getPoint(),new Point(3,0));
        assertEquals(myList.get(2).getPoint(),new Point(4,0));
    }

/*   @Test
    void getRightOarsIfGivenNumberBiggerThanActualOars(){
        List<Oar> myList = ship.getRightOars(5);
        assertTrue(myList.size()==maxLeftOars); //testing size
    }*/

    @Test
    void getAllOars(){
        ArrayList<Oar> allOars = ship.getOars();
        assertEquals(6,allOars.size());
    }
    

}