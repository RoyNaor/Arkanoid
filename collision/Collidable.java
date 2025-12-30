package collision;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import objects.Ball;

public interface Collidable {
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();//להחזיר את האיבר שאני אתנגש איתו

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity); // מחזיר את המהירות של הכדור אחרי ההתנגשות באיבר הספציפי
}
