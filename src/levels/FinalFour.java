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
 * A class representing game's level 4 - Final Four.
 */
public class FinalFour implements LevelInformation {
    private static final int BALL_SPEED = 8;
    private static final int BLOCKS_STARTING_X = 730;
    private static final int PADDLE_X = 360;
    private static final int PADDLE_Y = 570;
    private static final int BLOCKS_STARTING_Y = 100;
    private static final int BLOCKS_ROWS = 7;
    private static final int NUM_OF_BLOCKS = 15;
    private static final int ANGLE = 40;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private Background ffb;

    /**
     * Constructor.
     */
    public FinalFour() {
        this.ffb = new FinalFourBackground();
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(-ANGLE, BALL_SPEED));
        l.add(Velocity.fromAngleAndSpeed(10, BALL_SPEED));
        l.add(Velocity.fromAngleAndSpeed(ANGLE, BALL_SPEED));
        return l;
    }


    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this.ffb;
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW,
                Color.GREEN, new Color(253, 253, 254), Color.PINK,
                Color.cyan};
        List<Block> l = new ArrayList<>();
        int startingX = BLOCKS_STARTING_X;
        int startingY = BLOCKS_STARTING_Y;
        for (int i = 0; i < BLOCKS_ROWS; i++) {
            for (int j = 0; j < NUM_OF_BLOCKS; j++) {
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
        return (BLOCKS_ROWS * NUM_OF_BLOCKS);
    }

    @Override
    public Point paddleStartingPoint() {
        return new Point(PADDLE_X, PADDLE_Y);
    }
}
