package fr.unice.polytech.si3.qgl.sunny1000.game.properties;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.*;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Bateau;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Courant;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Entities;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Recif;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage.Wind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class Util {
    final static int INFINITY = 10000;
    final static private ObjectMapper objectMapper = new ObjectMapper();

    public static double calculateDistance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(),2)+Math.pow(p2.getY()-p1.getY(),2));
    }

    public static ObjectMapper getObjectMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static double calculateRotationAngle(double shipAngle,double checkAngle){
        shipAngle=angleParallelPositive(shipAngle);
        double finalAngle=checkAngle-shipAngle;
        finalAngle=adjustAngle(finalAngle);
        return finalAngle;
    }

    public static double calculateSommeAngle(double shipAngle,double checkAngle){
        shipAngle=angleParallelPositive(shipAngle);
        double finalAngle=checkAngle+shipAngle;
        finalAngle=adjustAngle(finalAngle);
        return finalAngle;
    }

    public static double angle(Point check,Point ship){
        double tangent;
        double angle;
        //yship-ycheck positive
        if(ship.getY()-check.getY()<0){
            //xship-xcheck positive
            if(check.getX()-ship.getX()>=0){
                tangent=(check.getY()-ship.getY())/(check.getX()-ship.getX());
                angle=Math.toDegrees(Math.atan(tangent));
            }
            //xship-xcheck negative
            else{
                tangent=((check.getY()-ship.getY())/(ship.getX()-check.getX()));
                angle=180-Math.toDegrees(Math.atan(tangent));
            }
        }else{
            if(check.getX()-ship.getX()>=0){
                tangent=(check.getY()-ship.getY())/(check.getX()-ship.getX());
                angle=Math.toDegrees(Math.atan(tangent));
            }
            //xship-xcheck negative
            else{
                double x= check.getX()-ship.getX();
                double y=check.getY()-ship.getY();
                tangent=y/x;
                angle=Math.toDegrees(Math.atan(tangent))-180;
            }
        }
        return angle;
    }

    public static double angleParallelPositive(double angle){
        if(angle<0){
            angle=360+angle;
        }
        return angle;
    }

    public static double adjustAngle(double angle){
        if(angle<=-180){
            angle+=360;
        }else if (angle>180){
            angle=360-angle;
        }
        return angle;
    }
    /** Vitesse des rames:

     -   Direction: direction du bateau
     -   Valeur: 165 x nombre de rames active / nombre total de rames
     **/

    public static double calculateOarSpeed(int activeOars, int availableOars, double shipOrientation){
        double speed;
        speed =  ((double)(165*activeOars) / availableOars);
        return speed;
    }

    /** Vitesse du vent:
     * -   Direction: direction du bateau
     *      -   Valeur: (nombre de voile ouverte / nombre de voile) x vitesse du vent x cosinus(angle entre la direction du vent et la direction du bateau)
     *
     */

    public static double calculateWindSpeed(Wind wind, double shipOrientation, int openedSails, int numberOfSails){
        double speed;
        speed =  ((double) openedSails / numberOfSails ) * wind.getStrength() * Math.cos( calculateRotationAngle(wind.getOrientation(), shipOrientation));
        return speed;
    }

    /**
     * Vitesse du courant:
     * Uniquement si le bateau est en contact avec un ou plusieurs courant.
     *      -   Direction: direction du courant
     *      -   Valeur: force du courant
     **/

    public double calculateStreamSpeed(Courant courant){
        double speed;
        speed =  courant.getStrength();
        return speed;

    }

    public static int calculateNumberOfSailors(double rotationAngle,final double MIN_ANGLE){
        rotationAngle= abs(rotationAngle);
        int i=0;
        while(rotationAngle> 45){
            i++;
            rotationAngle-= MIN_ANGLE;
        }
        return i;
    }


    public static String stringList(List<Point> points, Boolean x){
        String s="";
        for (Point p:
                points) {
            if(x)
                s+=p.getX() + ", ";
            else
                s+=p.getY() + ", ";
        }
        return s;
    }

    public static List<Point> calculateRectangleEdges(Rectangle rectangle, Position position){
        List<Point> edges=new ArrayList<>();
        double distanceBetweenCenterAndEdge= Math.sqrt(Math.pow(rectangle.getHeight()/2,2)+Math.pow(rectangle.getWidth()/2,2));
        double initAngle=Math.toDegrees(Math.atan((rectangle.getWidth()/2)/(rectangle.getHeight()/2)));
        double orientation=Math.toDegrees(position.getOrientation()+rectangle.getOrientation());
        double tab[]=new double[]{initAngle+orientation,180-initAngle+orientation,-initAngle+orientation,180+initAngle+orientation};
        for (double d: tab) {
            edges.add(calculatePoint(position.toPoint(),distanceBetweenCenterAndEdge,d));
        }

        Point[] edges2=new Point[]{edges.get(0),edges.get(1),edges.get(3),edges.get(2)};
        return Arrays.asList(edges2.clone());
    }

    public static Point calculatePoint(Point p,double distance,double angle){
        angle = toRadians(angle);
        double x;
        double y;
        x=p.getX()+distance*cos(angle);
        y=p.getY()+distance*sin(angle);
        return new Point(x,y);
    }
    /**
     * this methode finds if a given point belongs to a circle
     * @param center the center of the circle
     * @param radius the radius of the circle
     * @param point the given point
     * @return true if point is inside the circle false if not
     * */

    public static boolean belongsToCirle(Point center, double radius , Point point ){
        return Util.calculateDistance(point, center) <= radius;
    }

    /**
     * this methode finds if a given point belongs to a given rectangle
     * @param rectangle the given rectangle
     * @param position the position of the rectangle
     * @param p the given point
     * @return true if point is inside the circle false if not
     * */

    public static boolean belongsToRectangle(Rectangle rectangle,Position position, Point p){
        //return pointInRectangle(calculateRectangleEdges(rectangle,position).toArray(new Point[]{}),p);
        //return detectRectangleCollision(rectangle,position,p);
        return true;
    }

    /**
     * this methode finds there's a true statement in a list of booleans
     * @param collisions a list of booleans
     * @return true if there's a true statement in collisions, false if not
     * */
    private static boolean detectCollision(boolean[] collisions){
        for (int j = 0; j < 4; j++){
            if (collisions[j])
                return true;
        }
        return false;
    }


    /**
     * this methode finds if there's a collision between the ship and a checkpoint
     * @param ship the current ship
     * @param checkPointCenter the center of the checkpoint
     * @param checkPointRadius the radius of the checkpoint
     * @return true if the ship collides with the the entity
     * */
    public static boolean checkPointCollision(Ship ship, Point checkPointCenter, double checkPointRadius){
        List<Point> edges = calculateRectangleEdges(ship.getShape(),ship.getPosition());
        boolean[] collisions = new boolean[4];
        for (int i = 0; i < edges.size(); i++ ){
            collisions[i] = belongsToCirle(checkPointCenter,checkPointRadius,edges.get(i));
        }
        return detectCollision(collisions);
    }
}

