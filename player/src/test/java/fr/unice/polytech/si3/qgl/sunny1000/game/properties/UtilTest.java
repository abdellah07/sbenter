package fr.unice.polytech.si3.qgl.sunny1000.game.properties;

import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Deck;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Entity;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.*;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Entities;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Recif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void distanceBetweenTwoRandomPoints(){
        assertEquals(Math.sqrt(2),Util.calculateDistance(new Point(0,0),new Point(1,1)));
    }

    @Test
    void distanceBetweenTheSamePoint(){
        assertEquals(0,Util.calculateDistance(new Point(1,1), new Point(1,1)));
    }

    @Test
    void getAngleOfRandomCases(){
        Point ship = new Point(0,0);
        Point check = new Point(1,1);
        assertEquals(45,Util.angle(check,ship));
        check = new Point(-1,1);
        assertEquals(135,Util.angle(check,ship));
        check = new Point(-1,-1);
        assertEquals(-135,Util.angle(check,ship));
        check = new Point(1,-1);
        assertEquals(-45,Util.angle(check,ship));
    }

    @Test
    void getAngleIfBothShipAndCheckAtTheSameLine(){
        Point ship = new Point(0,0);
        Point check = new Point(1,0);
        assertEquals(0,Util.angle(check,ship));
        check = new Point(-1,0);
        assertEquals(-180,Util.angle(check,ship));
    }



    /*@Test
    void detectePolygonCollisionOnBorders(){
        Point point = new Point(3.99999999999,0);
        Point pointTest = new Point(4,0);
        Point pointTest2 = new Point(3.9,1.9);
        Point pointTest3 = new Point(0.1,0);
        Point pointTest4 = new Point(3,2);
        Point point1 = new Point(0,0);
        Point point3 = new Point(2,2);
        Point point4 = new Point(4,2);
        Point point5 = new Point(4,1);
        Point point6 = new Point(4,0);
        Point point8 = new Point(2,-1);
        Point[] polygon = {point1,point3,point4,point5,point6,point8};
        assertTrue(Util.belongsToPolygon(polygon,point));
        assertTrue(Util.belongsToPolygon(polygon,pointTest));
        assertTrue(Util.belongsToPolygon(polygon,pointTest2));
        assertTrue(Util.belongsToPolygon(polygon,point3));
        assertTrue(Util.belongsToPolygon(polygon,point4));
        assertTrue(Util.belongsToPolygon(polygon,point5));
        assertTrue(Util.belongsToPolygon(polygon,point6));


    }*/




    @Test
    void detectePolygonTest(){
        Point point1 = new Point(2120,3800);
        Point point2 = new Point(2300,4160);
        Point point3 = new Point(2260,4460);
        Point point4 = new Point(1960,4280);
        Point[] polygon1 = {point1,point3,point4,point2};
        Point point5 = new Point(2260,4460);
        Point point6 = new Point(2760,5260);
        Point point7 = new Point(2100,4960);
        Point point8 = new Point(1900,4460);
        Point point9 = new Point(1960,4280);
        Point[] polygon2 = {point5,point6,point7,point8,point9};

        Point point =  new Point(1981.6570345659184,4392.770221230585);
        assertTrue(Util.belongsToPolygon(polygon2,point));
        point =  new Point(2052.9755915335945,4562.759435695664);
        assertTrue(Util.belongsToPolygon(polygon2,point));
        point =  new Point(2124.283993691109,4732.750207109571);
        assertTrue(Util.belongsToPolygon(polygon2,point));
        point =  new Point(2052.9755915335945,4562.759435695664);
        assertTrue(Util.belongsToPolygon(polygon2,point));
        point =  new Point(2052.9755915335945,4562.759435695664);
        assertTrue(Util.belongsToPolygon(polygon2,point));

        Point pointTest =  new Point(1731.7708333333326+100,1731.7708333333326+100);
        assertTrue(Util.belongsToPolygon(polygon2,point));
        assertFalse(Util.belongsToPolygon(polygon2,pointTest));



    }


    /*@Test
    void detectePolygonCollisionInside(){
        Point pointDeTest1 = new Point(2,0);
        Point pointDeTest2 = new Point(2.5,1.5);
        Point point1 = new Point(0,0);
        Point point3 = new Point(2,2);
        Point point4 = new Point(4,2);
        Point point5 = new Point(4,1);
        Point point6 = new Point(4,0);
        Point point8 = new Point(2,-1);
        Point[] polygon = {point1,point3,point4,point5,point6,point8};
        assertTrue(Util.belongsToPolygon(polygon,pointDeTest1));
        assertTrue(Util.belongsToPolygon(polygon,pointDeTest2));
    }
*/

    @Test
    void detectePolygonCollisionOutside(){
        Point pointDeTest1 = new Point(5,0);
        Point pointDeTest2 = new Point(0,-2);
        Point pointDeTest3 = new Point(4.1,0);
        Point pointDeTest4 = new Point(2,-1.1);
        Point pointDeTest5 = new Point(-0.5,-0.5);
        Point pointDeTest6 = new Point(-0.1,0);
        Point point1 = new Point(0,0);
        Point point3 = new Point(2,2);
        Point point4 = new Point(4,2);
        Point point5 = new Point(4,1);
        Point point6 = new Point(4,0);
        Point point8 = new Point(2,-1);
        Point[] polygon = {point1,point3,point4,point5,point6,point8};
        assertFalse(Util.belongsToPolygon(polygon,pointDeTest1));
        assertFalse(Util.belongsToPolygon(polygon,pointDeTest2));
        assertFalse(Util.belongsToPolygon(polygon,pointDeTest3));
        assertFalse(Util.belongsToPolygon(polygon,pointDeTest4));
        assertFalse(Util.belongsToPolygon(polygon,pointDeTest5));
    }

    @Test
    void detectePolygone2(){
        Point pointDeTest1 = new Point(2.2,4.2);
        Point pointDeTest2 = new Point(1,-2.2);
        Point point1 = new Point(1,4);
        Point point3 = new Point(4,2);
        Point point4 = new Point(2,-2);
        Point point5 = new Point(-2,-4);
        Point point6 = new Point(-3,-3);
        Point point8 = new Point(-3,-2);
        Point point9 = new Point(-2,1);
        Point point10 = new Point(-1,3);
        Point[] polygon = {point1,point3,point4,point5,point6,point8,point9,point10};
        assertFalse(Util.belongsToPolygon(polygon,pointDeTest1));
        assertTrue(Util.belongsToPolygon(polygon,pointDeTest2));

    }

    @Test
    void detectePolygonCollisionInsideWithNegativePoints(){
        Point pointDeTest1 = new Point(-5.5,-1);
        Point pointDeTest2 = new Point(-6,-2);
        Point point1 = new Point(-6.5,1.5);
        Point point3 = new Point(-5.5,1.8);
        Point point4 = new Point(-7.6,-1.7);
        Point point5 = new Point(-5.5,-3.2);
        Point point6 = new Point(-3.2,-1.9);
        Point[] polygon = {point1,point3,point4,point5,point6};
        assertTrue(Util.belongsToPolygon(polygon,pointDeTest1));
        assertTrue(Util.belongsToPolygon(polygon,pointDeTest2));
    }

    @Test
    void belongsToCirleTest(){
        Point center = new Point(-2,1);
        Point testPoint1 = new Point(1,1);
        Point testPoint2 = new Point(-3,-2);
        Point testPoint3 = new Point(2,3);
        Point testPoint4 = new Point(-4.5,-3.5);
        assertTrue(Util.belongsToCirle(center,4,testPoint1));
        assertTrue(Util.belongsToCirle(center,4,testPoint2));
        assertFalse(Util.belongsToCirle(center,4,testPoint3));
        assertFalse(Util.belongsToCirle(center,4,testPoint4));
    }

    @Test
    void belongsToCirleTestInBorder(){
        Point center = new Point(-2,1);
        Point testPoint1 = new Point(-2,-3);;
        assertTrue(Util.belongsToCirle(center,4,testPoint1));
    }

    @Test
    void rectangleEdgesTest(){
        //public static List<Point> calculateRectangleEdges(Rectangle rectangle, Position position)
        Rectangle r=new Rectangle(500,1500,0);
        Position p = new Position(10690.10416666666,2421.8750000000005,0);
        //10690.10 - 250 2421.8750000000005-750
        List<Point> points=Util.calculateRectangleEdges(r,p);
        System.out.println(Util.stringList(points,true));
        System.out.println(Util.stringList(points,false));
        System.out.println();
        System.out.println(points);
        for (Point pt1:
                points) {
            for (Point pt2:
                    points) {
                double distance=Util.calculateDistance(pt1,pt2);
                System.out.println(distance);
            }
            System.out.println();
        }

    }

    @Test
    void belongsToRectangleTest(){
        Rectangle r=new Rectangle(500,1500,0);
        Position p = new Position(10690.10416666666,2421.8750000000005,0);
        int counter=0;
        List<Point> edges=Util.calculateRectangleEdges(r,p);
        for (int i = 0; i < 1500; i++) {
            edges.get(0).setX(edges.get(0).getX()+i);
            edges.get(0).setY(edges.get(0).getY());
            boolean b=Util.belongsToRectangle(r,p,edges.get(0));
            if (b)
                counter++;
        }
        System.out.println(counter);
    }

    @Test
    void allRectangleObstaclesTest(){
        Rectangle r=new Rectangle(500,1500,0);
        Position p = new Position(10690.10416666666,2421.8750000000005,1.0122909661567112);
        Util.getAllPoints(r,p);
    }



    @Test
    void calculatePointTest(){
        System.out.println(Util.calculatePoint(new Point(0,0),1000,40));
        System.out.println(Util.calculateDistance(new Point(0,0),new Point(525.3219888177298,850.9035245341184)));
        System.out.println(Math.sqrt(Math.pow(1500,2)+Math.pow(500,2)));
    }

    @Test
    public void speedTest(){
        System.out.println(Util.calculateOarSpeed(4,6,Math.toRadians(30)));
    }
}