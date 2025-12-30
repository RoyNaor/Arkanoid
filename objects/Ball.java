package objects;

// ID - 324946102
import biuoop.DrawSurface;
import collision.Collidable;
import collision.CollisionInfo;
import game.Game;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import sprites.Sprite;

import java.awt.*;

public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int)center.getX() , (int)center.getY(), r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    public void setVelocity(double dx, double dy){
        this.velocity.x = dx;
        this.velocity.y = dy;
    }

    public Velocity getVelocity(){
        return this.velocity;
    }

    public void setGameEnvironment(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    public void moveOneStep() {
        Point start = this.center;
        Point end = this.velocity.applyToPoint(start);
        Line line = new Line(start, end) ;

        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(line);

        if (collisionInfo == null) {
            this.center = end;
        }
        else {
            this.center = collisionInfo.collisionPoint();
            Collidable objectHit = collisionInfo.collisionObject();
            this.velocity = objectHit.hit(this,this.center, this.velocity);
        }
    }

    public void addToGame(Game g){
        g.addSprite(this);
    }

    public Color getColor() {
        return this.color;
    }
}
