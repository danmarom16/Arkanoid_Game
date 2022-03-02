// ID: 208995035
package levels;

import collidables.Block;
import gameutils.Velocity;
import geometry.Point;
import sprites.Sprite;
import java.util.List;

/**
 * An interface representing all the information that
 * any animation need to have.
 */
public interface LevelInformation {

    /**
     * Is the number of balls in the game.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * Initialize the velocities of the balls.
     *
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return speed of paddle.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return width of paddle.
     */
    int paddleWidth();

    /**
     * Returns the the level name will be displayed at the top of the screen.
     *
     * @return levels name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return levels background.
     */
    Sprite getBackground();

    /**
     * Returns the initial list of blocks.
     *
     * @return initial list of blocks.
     */
    List<Block> blocks();

    /**
     * Returns the Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blocks to remove before passing to next level.
     */
    int numberOfBlocksToRemove();

    /**
     * Returns the paddle upper Left point location.
     *
     * @return paddle's upper Left point location.
     */
    Point paddleStartingPoint();
}
