package game;

// ID - 324946102

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

    private List<Collidable> collidables = new ArrayList<>();

    public GameEnvironment() {
    }

    // add the given collidable to the environment.

    public void addCollidable(Collidable c){
        collidables.add(c);
    }

    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    public CollisionInfo getClosestCollision(Line trajectory) {

        Point closestPoint = null;
        Collidable closestObject =  null;
        double minDis = Double.MAX_VALUE;
        Rectangle rectCheck ;
        double dis = 0;

        for (int i= 0; i< collidables.size(); i++) {
            rectCheck = collidables.get(i).getCollisionRectangle();

            Point intersec = trajectory.closestIntersectionToStartOfLine(rectCheck) ;

            if (intersec != null) {
                dis = trajectory.start().distance(intersec) ;
                if (dis < minDis) {
                    minDis = dis;
                    closestPoint = intersec;
                    closestObject = collidables.get(i);
                }
            }
        }

        if (closestPoint == null) {
            return null;
        }

        return new CollisionInfo(closestPoint, closestObject);
    }
}
