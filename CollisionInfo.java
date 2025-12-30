public class CollisionInfo {

     private Point collidePoint;
     private Collidable collisionObject;

     public CollisionInfo(Point point, Collidable collisionObject) {
         this.collidePoint = point;
         this.collisionObject = collisionObject;
     }

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collidePoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return  collisionObject;
    }

}
