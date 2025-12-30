// ID - 324946102
package geometry;

public class Point {
    private double x, y;

        // constructor
        public Point(double x, double y) {
            this.x  = x;
            this.y  = y;
        }

        public double distance(Point other) {
            double xSum= (this.x- other.x)*(this.x- other.x);
            double ySum= (this.y- other.y)*(this.y- other.y);
            return Math.sqrt(xSum+ ySum) ;
        }

        public boolean equals(Point other) {
            if ((this.x == other.x)&&(this.y == other.y)) {
                    return true;  }
            return false;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }


}

