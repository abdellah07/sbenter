package fr.unice.polytech.si3.qgl.sunny1000.game;

import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void testCalculatePoint(){
        Point origine = new Point(0,0);
        Point destination = new Point(5,3);
        double distance= Util.calculateDistance(origine,destination);
        double angle = 31;
        System.out.println(Util.calculatePoint(origine,distance,angle));
    }
}
