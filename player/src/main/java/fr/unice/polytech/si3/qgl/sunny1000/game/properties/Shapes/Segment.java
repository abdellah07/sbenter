package fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes;

import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;

public class Segment {

    private Point max;
    private Point min;
    private double a;
    private double b;

    public Point getMax() {
        return max;
    }

    public Point getMin() {
        return min;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public Segment(Point max, Point min){
        this.max=max;
        this.min=min;
        if(max.getX() - min.getX()!=0)
            a = (max.getY() - min.getY()) / (max.getX() - min.getX());
        else
            a=Integer.MAX_VALUE;
        b = min.getY() - a * min.getX();


    }

    private boolean comprise(Point p,Point max,Point min){
        double maxX=Math.max(max.getX(),min.getX());
        double maxY=Math.max(max.getY(),min.getY());
        double minX=Math.min(max.getX(),min.getX());
        double minY=Math.min(max.getY(),min.getY());
        double x=p.getX();
        double y=p.getY();
        return (x<maxX && x>minX) && (y<maxY && y>minY);
    }

    private boolean comprise2(Point p, Point max, Point min){
        return Util.calculateDistance(p,max) + Util.calculateDistance(p,min) == Util.calculateDistance(min,max);
    }

    public boolean intersection(Segment segment){
        double x= (segment.getB()-this.getB())/(this.getA()-segment.getA());
        double y1=a*x+b;
        double y2= segment.getA()*x+ segment.getB();
        Point p1=new Point(x,y1);
        Point p2=new Point(x,y2);
        System.out.println(x);
        System.out.println(y1);
        System.out.println(y2);
        System.out.println();
        if ((this.getMin() == segment.getMin() && this.getMax() == segment.getMax()))
            return true;
        return y1<y2+0.000001 && y1>y2-0.000001 && comprise2(p2,segment.getMax(),segment.getMin());
    }




    public static void main(String[] args) {
        Segment s1=new Segment(new Point(2,4),new Point(2,-3));
        Segment s2=new Segment(new Point(-2,1),new Point(1,-3));
        System.out.println(s1.intersection(s2));
        System.out.println();
        Segment s3=new Segment(new Point(1,1),new Point(6,2));
        Segment s4=new Segment(new Point(1,2),new Point(6,1));
        System.out.println(s3.intersection(s4));
        System.out.println();
        Segment s5=new Segment(new Point(0,1),new Point(4,1.5));
        Segment s6=new Segment(new Point(0,3),new Point(4,2.5));
        System.out.println(s5.intersection(s6));

    }

}
