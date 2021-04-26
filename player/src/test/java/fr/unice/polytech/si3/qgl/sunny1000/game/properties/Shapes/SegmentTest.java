package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentTest {



    @Test
    void intersectionTestWithoutIntersection(){
        Segment s1=new Segment(new Point(2,4),new Point(2,-3));
        Segment s2=new Segment(new Point(-2,1),new Point(1,-3));
        assertFalse(s1.intersection(s2));
        Segment s3=new Segment(new Point(0,1),new Point(4,1.5));
        Segment s4=new Segment(new Point(0,3),new Point(4,2.5));
        assertFalse(s3.intersection(s4));
        Segment s5 =new Segment(new Point(-1,-1),new Point(0,3));
        Segment s6 =new Segment(new Point(0,3),new Point(1,-1));
        assertTrue(s5.intersection(s6));
        Segment s7 =new Segment(new Point(-1,-1),new Point(0,3));
        Segment s8 =new Segment(new Point(0.2,3),new Point(1,-1));
        assertFalse(s7.intersection(s8));
        Segment s9 =new Segment(new Point(0,0),new Point(0.5,0.5));
        Segment s10 =new Segment(new Point(3,0),new Point(3,6));
        assertFalse(s9.intersection(s10));
    }

    @Test
    void intersectionBetweenSegmentAndCircle(){
        Segment s1=new Segment(new Point(1,2),new Point(3,4));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s1,new Point(3,4),1.5));
        Segment s2=new Segment(new Point(0,0),new Point(3,0));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s2 ,new Point(3,-1),2));
        Segment s3=new Segment(new Point(1,0),new Point(1,4));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s3 ,new Point(2,2),1.41));
        Segment s4=new Segment(new Point(0,3),new Point(5,3));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s4 ,new Point(2,2),1.41));
        Segment s5=new Segment(new Point(0,4),new Point(5,4));
        assertFalse(Util.intersectionBetweenSegmentAndCircle(s5 ,new Point(2,2),1.41));
        Segment s6=new Segment(new Point(0,3),new Point(2,1));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s6 ,new Point(2,2),1.41));
        Segment s7 =new Segment(new Point(-1,1),new Point(4,1));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s7,new Point(1,-1),2));
        Segment s8 =new Segment(new Point(-1,1),new Point(4,-1));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s8,new Point(1,-1),2));
        Segment s9 =new Segment(new Point(-1,1),new Point(-1,-1));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s9,new Point(1,-1),2));
        Segment s10 =new Segment(new Point(-1,1),new Point(-2,-1));
        assertFalse(Util.intersectionBetweenSegmentAndCircle(s10,new Point(1,-1),2));
        Segment s11 =new Segment(new Point(-1,1),new Point(-2.1,-1));
        assertFalse(Util.intersectionBetweenSegmentAndCircle(s11,new Point(1,-1),2));
        Segment s12 =new Segment(new Point(-1,1),new Point(3,-2));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s12,new Point(1,-1),2));
        Segment s13 =new Segment(new Point(-1,1),new Point(-1,-1.9));
        assertTrue(Util.intersectionBetweenSegmentAndCircle(s13,new Point(1,-1),2));
    }

    @Test
    void polygonIntersection(){
        Point a = new Point(1,1);
        Point b = new Point(1,3);
        Point c = new Point(4,1);
        Point d = new Point(4,3);
        Point[] polygon = new Point[]{a,b,c,d};
        Segment segment = new Segment(new Point(0,2),new Point(3,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment,polygon));
        Segment segment2 = new Segment(new Point(2,2),new Point(2,4));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment2,polygon));
        Segment segment3 = new Segment(new Point(2,2),new Point(5,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment3,polygon));
        Segment segment4 = new Segment(new Point(2,2),new Point(2,-1));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment4,polygon));
        Segment segment5 = new Segment(new Point(3,-1),new Point(3,5));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment5,polygon));
        Segment segment6 = new Segment(new Point(-1,2),new Point(6,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment6,polygon));
        Segment segment7 = new Segment(new Point(0,4),new Point(2,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment7,polygon));
        Segment segment8 = new Segment(new Point(3,4),new Point(5,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment8,polygon));
        Segment segment9 = new Segment(a,b);
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment9,polygon));
        Segment segment10 = new Segment(new Point(0,3),new Point(7,3));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment10,polygon));
        Segment segment11 = new Segment(new Point(-1,1),new Point(0,1));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment11,polygon));
        Segment segment12 = new Segment(new Point(0,0),new Point(0.8,0.8));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment12,polygon));
        Segment segment13 = new Segment(new Point(5,0),new Point(4.2,1.8));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment13,polygon));
        Segment segment14 = new Segment(new Point(7,2),new Point(4.2,2));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment14,polygon));
        Segment segment15 = new Segment(new Point(0.8,0),new Point(0.8,4));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment15,polygon));
        Segment segment16 = new Segment(new Point(3,2),new Point(6,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment16,polygon));
        Segment segment17 = new Segment(new Point(2,4),new Point(5,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment17,polygon));
        Segment segment18 = new Segment(new Point(2,4),new Point(2,-2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment18,polygon));
        Segment segment19 = new Segment(new Point(2,4),new Point(2,2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment19,polygon));
    }

    @Test
    void segmentPolygon2(){
        Point a = new Point(-2,1);
        Point b = new Point(2,1);
        Point c = new Point(3,-1);
        Point d = new Point(0,-3);
        Point e = new Point(-3,-1);
        Point[] polygon = new Point[]{a,b,c,d,e};
        Segment segment = new Segment(new Point(-3,-2),new Point(3,-2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment,polygon));
        Segment segment2 = new Segment(new Point(-3,-3),new Point(3,-3));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment2,polygon));
        Segment segment3 = new Segment(new Point(-3,-3.5),new Point(3,-3.5));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment3,polygon));
        Segment segment4 = new Segment(new Point(2.5,-1),new Point(2.5,-3));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment4,polygon));
        Segment segment5 = new Segment(new Point(3,0.5),new Point(-3,0.5));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment5,polygon));
        Segment segment6 = new Segment(new Point(-2.8,1),new Point(-2.8,-2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment6,polygon));
        Segment segment7 = new Segment(new Point(-3.2,1),new Point(-3.2,-2));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment7,polygon));
        Segment segment8 = new Segment(new Point(-1,-1),new Point(1,-1));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment8,polygon));
        Segment segment9 = new Segment(new Point(-1,0),new Point(1,0));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment9,polygon));
        Segment segment10 = new Segment(new Point(-1,-2),new Point(1,0));
        assertFalse(Util.intersectionBetweenSegmentAndPolygon(segment10,polygon));
    }

    @Test
    void calculateRectangleEdgesTest(){
        Position position = new Position(2,2,0);
        Rectangle rectangle = new Rectangle(2,2,0);
        System.out.println(Util.calculateRectangleEdges(rectangle,position));
        Position position2 = new Position(2,-2,0);
        Rectangle rectangle2 = new Rectangle(2,6,0);
        System.out.println(Util.calculateRectangleEdges(rectangle2, position2));
        Point a = new Point(-1,-1);
        Point b = new Point(5,-1);
        Point c = new Point(-1,-3);
        Point d = new Point(5,-3);
        Point[] polygon = new Point[]{d,c,b,a};
        Segment segment6 = new Segment(new Point(0,-2),new Point(2,-2));
        assertTrue(Util.intersectionBetweenSegmentAndPolygon(segment6,polygon));
    }

    @Test
    void segmentRectangleTest(){
        Position position = new Position(7,9,0);
        Rectangle rectangle = new Rectangle(4,8,0);

        /*Segment segment = new Segment(new Point(1,0),new Point(1,-4));
        Segment segment2 = new Segment(new Point(6,0),new Point(6,-4));
        Segment segment3 = new Segment(new Point(-2,-1),new Point(7,-1));
        Segment segment4 = new Segment(new Point(-2,-2),new Point(7,-2));
        Segment segment5 = new Segment(new Point(4,0),new Point(5.5,-2));
        Segment segment6 = new Segment(new Point(-0.5,-2),new Point(2,-2));
        Point[] edges= Util.calculateRectangleEdges(rectangle, position).toArray(new Point[0]);
        assertTrue(Util.intersectionBetweenSegmentAndRectangle(segment,edges));
        assertFalse(Util.intersectionBetweenSegmentAndRectangle(segment2,edges));
        assertTrue(Util.intersectionBetweenSegmentAndRectangle(segment3,edges));
        assertTrue(Util.intersectionBetweenSegmentAndRectangle(segment4,edges));
        assertTrue(Util.intersectionBetweenSegmentAndRectangle(segment5,edges));*/
        Position shipPosition=new Position(0,0,0);

        int shipOrientation=32;
        System.out.println(Util.intersectionShipRectangle(shipPosition,32,rectangle,position));

    }

    @Test
    void intersectionTestWithIntersection(){
        Segment s1=new Segment(new Point(1,1),new Point(6,2));
        Segment s2=new Segment(new Point(1,2),new Point(6,1));
        assertTrue(s1.intersection(s2));
        Segment s3 =new Segment(new Point(-1,-1),new Point(5,1));
        Segment s4 =new Segment(new Point(-1,-2),new Point(0,1));
        assertTrue(s4.intersection(s3));
        Segment s5 =new Segment(new Point(-1,-1),new Point(0,3));
        Segment s6 =new Segment(new Point(0,3),new Point(1,-1));
        assertTrue(s5.intersection(s6));
        Segment s7 =new Segment(new Point(-1,-1),new Point(3,-0.8));
        Segment s8 =new Segment(new Point(1,-0.8),new Point(3,-1));
        assertTrue(s7.intersection(s8));
        Segment s9 =new Segment(new Point(1,-1),new Point(1,-4));
        Segment s10 =new Segment(new Point(0.8,0),new Point(1.2,-4));
        assertTrue(s9.intersection(s10));


        Segment s =new Segment(new Point(0,0),new Point(67844.84769251407,42392.541138656394));
        Segment s90 =new Segment(new Point(11,7),new Point(3,7));
        assertTrue(s.intersection(s90));


    }
}
