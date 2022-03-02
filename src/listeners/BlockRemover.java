// ID: 208995035
package listeners;

import collidables.Ball;
import collidables.Block;
import gameutils.Counter;
import levels.GameLevel;

/**
 * A listeners BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * A constructor for ball remover.
     *
     * @param gameLevel is the game to remove ball from.
     * @param removedBlocks is the remaining blocks counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
        gameLevel.getRemainingBlocksCounter().decrease(1);
    }
}
