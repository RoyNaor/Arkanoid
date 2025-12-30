package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collision.Collidable;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.*;
import java.util.Random;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Paddle paddle;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;

    public Game(){
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        //this.blockCounter = blockCounter;
    }

    public void addCollidable(Collidable c){
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s){
        sprites.addSprite(s);
    }

    public void initialize() {
        this.gui = new GUI("Board",800,600);

        this.sleeper = new Sleeper();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();

        BlockRemover blockRemover = new BlockRemover(this, blockCounter) ;
        BallRemover ballRemover = new BallRemover(this, ballCounter) ;
        Block deathBlock = new Block(new Rectangle(new Point(0, 600), 800, 30),Color.BLACK);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
        ScoreTrackingListener scoreTrackingListener =  new ScoreTrackingListener(scoreCounter);


        Point center = new Point(400, 300);
        Ball ball1 = new Ball(center, 7, Color.PINK);
        ball1.setVelocity(2,2);
        ball1.setGameEnvironment(environment);
        ball1.addToGame(this);
        ballCounter.increase(1);

        Point center2 = new Point(500, 300);
        Ball ball2 = new Ball(center2, 7, Color.PINK);
        ball2.setVelocity(2,2);
        ball2.setGameEnvironment(environment);
        ball2.addToGame(this);
        ballCounter.increase(1);


        Point center3 = new Point(450, 350);
        Ball ball3 = new Ball(center3, 7, Color.PINK);
        ball3.setVelocity(2,2);
        ball3.setGameEnvironment(environment);
        ball3.addToGame(this);
        ballCounter.increase(1);


        Block Top = new Block(new Rectangle(new Point(0, 0), 800, 30),Color.PINK);
        Block Left = new Block(new Rectangle(new Point(0, 30), 30, 560), Color.PINK);
        Block Right = new Block(new Rectangle(new Point(770, 30), 30, 560),Color.PINK);

        Top.addToGame(this);
        Left.addToGame(this);
        Right.addToGame(this);
        //blockCounter.increase(3);
        ScoreIndicator ind = new ScoreIndicator(scoreCounter);
        ind.addToGame(this);

        Color[] colors = {
                Color.MAGENTA,
                Color.PINK,
                Color.lightGray,
                Color.magenta,
                Color.white
        };
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            Color color = colors[i];
            for (int j =0 ; j < 10 - i; j ++) {
                Rectangle rectangle = new Rectangle(new Point(710 - j*50, 70 + i*25 ) ,50, 25) ;
                Block block = new Block(rectangle, color);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                blockCounter.increase(1);
            }
        }

        Rectangle paddrect = new Rectangle(new Point(350, 560), 120, 20);
        Paddle p = new Paddle(this.gui.getKeyboardSensor(),paddrect);
        p.addToGame(this);
    }

    // Run the game -- start the animation loop.
    public void run(){

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (blockCounter.getValue() == 0) {
                scoreCounter.increase(100);
                System.out.println("You Win!\nYour score is: " + scoreCounter.getValue());
                gui.close();
                return;
            }
            if(ballCounter.getValue() == 0) {
                System.out.println("Game Over.\nYour score is: " + scoreCounter.getValue());
                gui.close();
                return;
            }
        }
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);;
    }

    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }

}
