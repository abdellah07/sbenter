package fr.unice.polytech.si3.qgl.sunny1000.game.properties;

import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage.Wind;

import java.util.ArrayList;
import java.util.List;

public class CalculatePath {
    /**
     * Position initiale: [0;0]
     * Orientation initiale: 0
     * N = 10
     * Vitesse linéaire = 100
     * Rotation = 1
     */
    Point cord;
    double orientationInitial;
    final int N;
    double oarSpeed;
    double rotation ;
    Wind wind;
    int activeSail;
    Ship ship;

    public CalculatePath(Point cord, double orientationInitial, int n, Wind wind,int activeSail, double rotation, Ship ship,double oarSpeed) {
        this.cord = cord;
        this.orientationInitial = orientationInitial;
        double windSpeed = Util.calculateWindSpeed(wind, ship.getPosition().getOrientation(), activeSail, 1);
        this.wind = wind;
        this.activeSail=activeSail;
        this.
        N = n;
        this.oarSpeed = oarSpeed;
        this.rotation = rotation;
        this.ship=ship;
    }

    public CalculatePath(Point cord, double orientationInitial, int n, double oarSpeed, double rotation) {
        this.cord = cord;
        this.orientationInitial = orientationInitial;
        N = n;
        this.oarSpeed = oarSpeed;
        this.rotation = rotation;
    }

    public double getTotalRotation() {
        return rotation+orientationInitial;
    }


    public List<Point> generateNextPos(){

        double x=cord.getX();
        double y=cord.getY();
        double ri=orientationInitial;
        double windSpeed ;
        List<Point> points=new ArrayList<>();
        for (int i = 0; i < N; i++) {
            windSpeed = Util.calculateWindSpeed(wind, ri, activeSail, 1);
            double v=windSpeed+oarSpeed;
            double vi=v/N;

            x+=Math.cos(ri)*vi;
            y+=Math.sin(ri)*vi;
            points.add(new Point(x,y));
            ri+=rotation/N;
        }
        return points;
    }



    public Point generateNextPosDegret2(){
        double x=cord.getX();
        double y=cord.getY();
        double ri=orientationInitial;

        for (int i = 0; i < N; i++) {
            double vi=oarSpeed/N;
            ri+=rotation/N;
            x+=Math.cos(ri)*vi;
            y+=Math.sin(ri)*vi;
        }
        return new Point(x,y);
    }

    public Point generateNextPosRadian3(){
        double x=cord.getX();
        double y=cord.getY();
        double ri=orientationInitial;

        for (int i = 0; i < N; i++) {
            double vi=oarSpeed/N;
            ri+=rotation/N;
            x+=Math.cos(Math.toRadians(ri))*vi;
            y+=Math.sin(Math.toRadians(ri))*vi;
        }
        return new Point(x,y);
    }

    @Override
    public String toString() {
        return "CalculatePath{" +
                "cord=" + cord +
                ", orientationInitial=" + orientationInitial +
                ", N=" + N +
                ", v=" + oarSpeed +
                ", rotation=" + rotation +
                '}';
    }

}

