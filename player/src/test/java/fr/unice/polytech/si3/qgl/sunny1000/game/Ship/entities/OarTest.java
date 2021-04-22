package fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OarTest {
    Oar oar;
    List<Sailor> mySailors;

    @BeforeEach
    void setOar(){
        oar = new Oar("Oar",1,1);
        mySailors = new ArrayList<>();
        mySailors.add(new Sailor(0,0,0,"Luffy"));
        mySailors.add(new Sailor(0,1,0,"Zoro"));
    }

    @Test
    void findNearestSailor(){
        assertEquals("Zoro",oar.findNearestSailor(mySailors).getName());
    }

    @Test
    void findNearestSailorWhenThereIsNoSailor(){
        mySailors.clear();
        assertEquals(null,oar.findNearestSailor(mySailors));
    }

    @Test
    void findNearestSailorWhenTwoAtTheSameMinimalDistance(){
        mySailors.add(new Sailor(2,1,0,"Sanji"));
        assertEquals("Zoro",oar.findNearestSailor(mySailors).getName()); // First to have the minimal distance
    }

}