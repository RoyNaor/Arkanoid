// ID - 324946102
public class Velocity {
    public double x;
    public double y;

    public Velocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dx = speed * Math.sin(radian);
        double dy = -speed * Math.cos(radian);
        return new Velocity(dx, dy);
    }


    public Point applyToPoint(Point p) {
        double sumX =  x + p.getX();
        double sumY =  y + p.getY();
        return new Point(sumX, sumY) ;
    }
}