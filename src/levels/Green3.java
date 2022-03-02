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
 * A class representing game's level 3 - Green3.
 */
public class Green3 implements LevelInformation {

    private Background g3b;

    private static final int PADDLE_X = 360;
    private static final int PADDLE_Y = 570;
    private static final int BLOCKS_STARTING_X = 730;
    private static final int BLOCKS_STARTING_Y = 100;
    private static final int BLOCKS_ROWS = 5;
    private static final int NUM_OF_BLOCKS = 10;
    private static final int ANGLE = 40;
    private static final int SPEED = 6;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;

    /**
     * Constructor.
     */
    public Green3() {
        this.g3b = new Green3Background();
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(-ANGLE, SPEED);
        Velocity v2 = Velocity.fromAngleAndSpeed(ANGLE, SPEED);
        l.add(v1);
        l.add(v2);
        return l;
    }

    @Override
    public int paddleSpeed() {
        return SPEED;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.g3b;
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.blue,
                Color.LIGHT_GRAY};

        List<Block> l = new ArrayList<>();
        int startingX = BLOCKS_STARTING_X;
        int startingY = BLOCKS_STARTING_Y;
        for (int i = 0; i < BLOCKS_ROWS; i++) {
            for (int j = i; j < NUM_OF_BLOCKS; j++) {
                l.add(new Block(new Point(startingX, startingY), BLOCK_WIDTH, BLOCK_HEIGHT, colors[i]));
                startingX = startingX - BLOCK_WIDTH;
            }
            startingY += BLOCK_HEIGHT;
            startingX = BLOCKS_STARTING_X;
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public Point paddleStartingPoint() {
        return new Point(PADDLE_X, PADDLE_Y);
    }
}
