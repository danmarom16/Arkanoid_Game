// ID: 208995035
package listeners;

import collidables.Ball;
import collidables.Block;
import gameutils.Counter;

/**
 * A Listener class that tracks the score of the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor for ScoreTrackingListener.
     * @param scoreCounter is the current game score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Adds 5 points to counter when a hit had occured.
     * @param beingHit is the block that is being hit.
     * @param hitter is the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
