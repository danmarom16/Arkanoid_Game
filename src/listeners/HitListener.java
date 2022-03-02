// ID: 208995035
package listeners;

import collidables.Ball;
import collidables.Block;

/**
 * An Interface representing hit listeners.
 * Classes that implement it operate when a hit had occurred.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the collidables.Ball that's doing the hitting.
     *
     * @param beingHit is the block that is being hit.
     * @param hitter is the hitter ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
