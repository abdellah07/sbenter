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
    public boolean comprise(Point p,Point max,Point min){
        double maxX=Math.max(max.getX(),min.getX());
        double maxY=Math.max(max.getY(),min.getY());
        double minX=Math.min(max.getX(),min.getX());
        double minY=Math.min(max.getY(),min.getY());
        System.out.println(maxX);
        System.out.println(maxY);
        System.out.println(minX);
        System.out.println(minY);
        double x=p.getX();
        double y=p.getY();
        return (x<maxX && x>minX) && (y<maxY && y>minY);
    }

    public boolean comprise2(Point p, Point max, Point min){
        return !(Util.calculateDistance(p,max) > Util.calculateDistance(min,max) ||  Util.calculateDistance(p,min) > Util.calculateDistance(min,max));
    }

    /*public boolean intersection(Segment segment){
        double x= (segment.getB()-this.getB())/(this.getA()-segment.getA());
        double y1=a*x+b;
        double y2= segment.getA()*x+ segment.getB();
        Point p1=new Point(x,y1);
        Point p2=new Point(x,y2);
        if ((this.getMin() == segment.getMin() && this.getMax() == segment.getMax()))
            return true;
        return y1<y2+0.000001 && y1>y2-0.000001 && comprise2(p2,segment.getMax(),segment.getMin());
    }*/

    public boolean intersection(Segment segment){
        double det = ((this.getMax().getX() - this.getMin().getX()) * (segment.getMax().getY() - segment.getMin().getY())) - ((segment.getMax().getX() - segment.getMin().getX()) * (this.getMax().getY() - this.getMin().getY()));
        if (det == 0) return false;
        double t1 = (((segment.getMax().getX() - this.getMin().getX()) * (segment.getMax().getY() - segment.getMin().getY())) - ((segment.getMax().getX() - segment.getMin().getX()) * (segment.getMax().getY() - this.getMin().getY()))) / det ;
        double t2 = (((this.getMax().getX() - this.getMin().getX()) * (segment.getMax().getY() - this.getMin().getY())) - ((segment.getMax().getX() - this.getMin().getX()) * (this.getMax().getY() - this.getMin().getY()))) / det ;
        return !(t1 > 1) && !(t1 < 0) && !(t2 > 1) && !(t2 < 0);
    }


}
