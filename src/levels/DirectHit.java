// ID: 208995035
package levels;

import collidables.Block;
import gameutils.Velocity;
import geometry.Point;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing game's level 1 - Direct Hit.
 */
public class DirectHit implements LevelInformation {
    private static final int PADDLE_X = 360;
    private static final int PADDLE_Y = 570;
    private Background dhb;

    /**
     * Constructor.
     */
    public DirectHit() {
        this.dhb = new DirectHitBackground();
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(2, 6));
        return l;
    }


    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "DirectHit";
    }

    @Override
    public Sprite getBackground() {
        return this.dhb;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<>();
        Block b = new Block(new Point(405, 150), 40, 40, Color.red);
        l.add(b);
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Point paddleStartingPoint() {
        return new Point(PADDLE_X, PADDLE_Y);
    }
}
