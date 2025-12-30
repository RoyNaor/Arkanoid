package objects;

import biuoop.DrawSurface;
import collision.Collidable;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import game.Game;
import sprites.Sprite;

import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    List<HitListener> hitListeners;

    public Block(Rectangle rectangle, Color color){
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();

    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double minX = this.rectangle.getUpperLeft().getX();
        double maxX = this.rectangle.getUpperLeft().getX()+ this.rectangle.getWidth();
        double minY = this.rectangle.getUpperLeft().getY();
        double maxY = this.rectangle.getUpperLeft().getY() +this.rectangle.getHeight();

        double newDx = currentVelocity.x;
        double newDy = currentVelocity.y;
        double epsilon = 0.001;

        if (Math.abs(collisionPoint.getX() - minX) < epsilon || Math.abs(collisionPoint.getX() - maxX) < epsilon) {//לא רצה המטלה בלי האפסילון הזה
           newDx = -newDx;
        }

        if (Math.abs(collisionPoint.getY() - minY) < epsilon || Math.abs(collisionPoint.getY() - maxY) < epsilon) {
            newDy = -newDy;
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return new Velocity(newDx, newDy) ;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);

        int x =(int)this.rectangle.getUpperLeft().getX();
        int y =(int)this.rectangle.getUpperLeft().getY();
        int width = (int)this.rectangle.getWidth();
        int height = (int)this.rectangle.getHeight();

        d.fillRectangle(x, y, width, height);

        d.setColor(Color.black);
        d.drawRectangle(x,y,width,height);
    }

    @Override
    public void timePassed() {

    }

    public void addToGame(Game g){
        g.addCollidable(this);
        g.addSprite(this);
    }



    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    public void removeFromGame(Game game){
       game.removeCollidable(this);
       game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl) ;
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
}

}
