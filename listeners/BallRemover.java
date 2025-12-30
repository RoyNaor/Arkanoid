package listeners;

import game.Counter;
import game.Game;
import objects.Ball;
import objects.Block;

public class BallRemover implements HitListener {
    Game game;
    Counter remainedballs; ;


    public BallRemover(Game game, Counter remainedballs) {
        this.game = game;
        this.remainedballs = remainedballs;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        remainedballs.decrease(1);
    }
}
