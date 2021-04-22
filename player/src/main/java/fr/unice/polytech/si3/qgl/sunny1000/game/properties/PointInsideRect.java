package fr.unice.polytech.si3.qgl.sunny1000.game.properties;
import static java.lang.Math.sqrt;

public class PointInsideRect {
    private static double square(double n)
    {
        return n*n;
    }
    private static double areaOfTriangle(
            double xa, double ya,
            double xb, double yb,
            double px, double py )
    {
        double side1 = sqrt(square(ya-yb) + square(xa-xb));
        double side2 = sqrt(square(ya-py) + square(xa-px));
        double side3 = sqrt(square(yb-py) + square(xb-px));

        double semi_perimeter = (side1 + side2 + side3) / 2;

        return sqrt(semi_perimeter
                * ( semi_perimeter - side1 )
                * ( semi_perimeter - side2 )
                * ( semi_perimeter - side3 ));
    }

    private static double areaOfRect(
            double x1, double y1,
            double x2, double y2,
            double x3, double y3,
            double x4, double y4 )
    {
        double side1 = sqrt(square(y1-y2) + square(x1-x2));
        double side2 = sqrt(square(y2-y3) + square(x2-x3));
        return side1 * side2;
    }

    public boolean check(
            double x1, double y1,
            double x2, double y2,
            double x3, double y3,
            double x4, double y4,
            double pointX, double pointY)
    {
        double tri1Area = areaOfTriangle(x1,y1, x2,y2, pointX,pointY);
        double tri2Area = areaOfTriangle(x2,y2, x3,y3, pointX,pointY);
        double tri3Area = areaOfTriangle(x3,y3, x4,y4, pointX,pointY);
        double tri4Area = areaOfTriangle(x4,y4, x1,y1, pointX,pointY);

        double rectArea = areaOfRect(x1,y1, x2,y2, x3,y3, x4,y4);

        double triAreaSum = tri1Area + tri2Area + tri3Area+ tri4Area;


        if(triAreaSum % Math.pow(10, 14) >= 0.999999999999999)
        {
            triAreaSum = Math.ceil(triAreaSum);
            System.out.println(triAreaSum);
        }
        return triAreaSum == rectArea;
    }
}