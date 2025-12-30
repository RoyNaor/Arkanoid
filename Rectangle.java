import java.util.ArrayList;
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
     //with the specified line.
    public java.util.List<Point> intersectionPoints(Line line){

        ArrayList<Point> intersecList = new ArrayList<>() ;

        double upperRightX = upperLeft.getX() + width;
        double upperRightY = upperLeft.getY() ;
        double downLeftX = upperLeft.getX();
        double downLeftY = upperLeft.getY() + height;
        double downRightX = upperLeft.getX() + width;
        double downRightY =upperLeft.getY() + height;

        Line Top= new Line(upperLeft.getX(), upperLeft.getY(), upperRightX,upperRightY );
        Line Bottom = new Line(downLeftX, downLeftY, downRightX, downRightY );
        Line Left = new Line(upperLeft.getX(), upperLeft.getY(), downLeftX, downLeftY  );
        Line Right = new Line(upperRightX, upperRightY, downRightX, downRightY ) ;

        if (Top.intersectionWith(line) != null) {
            intersecList.add(Top.intersectionWith(line));
        }

        if (Bottom.intersectionWith(line) != null) {
            intersecList.add(Bottom.intersectionWith(line));
        }

        if (Left.intersectionWith(line) != null) {
            intersecList.add(Left.intersectionWith(line));
        }

        if (Right.intersectionWith(line) != null) {
            intersecList.add(Right.intersectionWith(line));
        }

        return intersecList;
    }


    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperLeft;
    }

    public Line getLeft() {
        double downLeftX = upperLeft.getX();
        double downLeftY = upperLeft.getY() + height;
        Line Left = new Line(upperLeft.getX(), upperLeft.getY(), downLeftX, downLeftY);
        return Left;
    }

    public Line getrRight() {
        double upperRightX = upperLeft.getX() + width;
        double upperRightY = upperLeft.getY();
        double downRightX = upperRightX;
        double downRightY = upperLeft.getY() + height;
        Line Right = new Line(upperRightX, upperRightY, downRightX, downRightY);
        return Right;
    }

    public Line getTop() {
        double upperRightX = upperLeft.getX() + width;
        double upperRightY = upperLeft.getY();
        Line Top = new Line(upperLeft.getX(), upperLeft.getY(), upperRightX, upperRightY);
        return Top;
    }

    public Line getBottom() {
        double downLeftX = upperLeft.getX();
        double downLeftY = upperLeft.getY() + height;
        double downRightX = upperLeft.getX() + width;
        double downRightY = upperLeft.getY() + height;
        Line Bottom = new Line(downLeftX, downLeftY, downRightX, downRightY);
        return Bottom;
    }
}
