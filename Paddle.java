import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Paddle implements Collidable, Sprite {
    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;

    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle){
        this.keyboard = keyboard;
        this.rectangle = rectangle;
    }

    public void moveLeft(){
        double newX = this.rectangle.getUpperLeft().getX() - 8;
        if (newX + this.rectangle.getWidth() <= 20) {
            newX = 800 - rectangle.getWidth();
        }

        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),this.rectangle.getHeight());
    }

    public void moveRight(){
        double newX = this.rectangle.getUpperLeft().getX() + 8;
        if (newX >= 780) {
            newX = 20;
        }

        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),this.rectangle.getHeight());
    }

    @Override
    public void timePassed(){
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d){
        d.setColor(Color.MAGENTA);

        int x =(int)this.rectangle.getUpperLeft().getX();
        int y =(int)this.rectangle.getUpperLeft().getY();
        int width = (int)this.rectangle.getWidth();
        int height = (int)this.rectangle.getHeight();

        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x,y,width,height);
    }

    @Override
    public Rectangle getCollisionRectangle(){
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity){
        double paddleWidth = this.rectangle.getWidth();
        double startX = this.rectangle.getUpperLeft().getX();
        double hitX = collisionPoint.getX();

        double sectionWidth = paddleWidth / 5;
        int section = (int)((hitX - startX) / sectionWidth);
        double speed = Math.sqrt(Math.pow(currentVelocity.x,2) + Math.pow(currentVelocity.y, 2));

        switch (section) {
            case 0:
                return Velocity.fromAngleAndSpeed(300, speed);
            case 1:
                return Velocity.fromAngleAndSpeed(330, speed);
            case 2:
                return new Velocity(currentVelocity.x, -currentVelocity.y);
            case 3:
                return Velocity.fromAngleAndSpeed(30, speed);
            case 4:
                return Velocity.fromAngleAndSpeed(60, speed);
            default:
                return new Velocity(currentVelocity.x, -currentVelocity.y);
        }
    }

    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}

