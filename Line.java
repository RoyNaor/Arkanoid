import java.util.List;

public class Line {

    private Point start, end;

    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start= new Point(x1,y1);
        this.end= new Point(x2,y2);
    }

    // Return the length of the line
    public double length() {
        return start.distance(end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double xNew = (this.start.getX()+this.end.getX())/2 ;
        double yNew = (this.start.getY()+this.end.getY())/2 ;;
        return new Point(xNew,yNew);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    // Returns true if this 2 lines intersect with this line, false otherwise
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

        if (d == 0 ) return null;

        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / d;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / d;

        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            double interX = x1 + ua * (x2 - x1);
            double interY = y1 + ua * (y2 - y1);
            return new Point(interX, interY);
        }

        return null;
    }

    // equals -- return true if the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }

        return false;
    }

    public Point closestIntersectionToStartOfLine(Rectangle rect){
        List<Point> intersecList = rect.intersectionPoints(this);

        if (intersecList.isEmpty()) {
            return null;
        }
        Point closest = null;
        double minDist = Double.MAX_VALUE;

        for (int i = 0; i < intersecList.size(); i++) {
            double dis = start.distance(intersecList.get(i));

            if (dis < 0.00001) {
                continue;
            }

            if (dis < minDist) {
                minDist = dis;
                closest = intersecList.get(i);
            }
        }

        return closest;
    }
}
