// ID: 208995035
package listeners;

import collidables.Ball;
import collidables.Block;
import gameutils.Counter;
import levels.GameLevel;

/**
 * A class representing ball remover listener.
 * Handles the logic of removing a block from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor for BallRemover.
     *
     * @param gameLevel is the game to remove ball from.
     * @param remainingBalls is the remaining balls counter
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        gameLevel.getRemainingBallsCounter().decrease(1);
    }
}
