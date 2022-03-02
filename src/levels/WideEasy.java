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
 * A class representing game's 2nd level - Wide Easy.
 */
public class WideEasy implements LevelInformation {

    private static final int BLOCKS_STARTING_X = 20;
    private static final int BLOCKS_Y_AXIS = 200;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int CONST = 5;

    private static final int LANGLE = -40;
    private static final int RANGLE = 10;
    private static final int CHANGE = 10;
    private static final int SPEED = 6;

    private static final int BALL_X = 150;
    private static final int BALL_Y = 400;

    private static final int PADDLE_X = 60;
    private static final int PADDLE_Y = 570;

    private Background web;

    /**
     * Constructor.
     */
    public WideEasy() {
        this.web = new WideEasyBackground();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        int langle = LANGLE;
        int rangle = RANGLE;

        // left
        for (int i = 0; i < 5; i++) {
            v.add(Velocity.fromAngleAndSpeed(langle, SPEED));
            langle += CHANGE;
        }
        // right
        for (int i = 0; i < 5; i++) {
            v.add(Velocity.fromAngleAndSpeed(rangle, SPEED));
            rangle += CHANGE;
        }
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 3;
    }

    @Override
    public int paddleWidth() {
        return 700;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this.web;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<>();
        int xAxis = BLOCKS_STARTING_X;
        // red blocks
        l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), (BLOCK_WIDTH + CONST), BLOCK_HEIGHT, Color.red));
        xAxis += BLOCK_WIDTH + CONST;
        l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.red));
        xAxis += BLOCK_WIDTH;

        // orange
        for (int i = 0; i < 2; i++) {
            l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.orange));
            xAxis += BLOCK_WIDTH;
        }

        // yellow
        for (int i = 0; i < 2; i++) {
            l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.yellow));
            xAxis += BLOCK_WIDTH;
        }

        // green
        for (int i = 0; i < 3; i++) {
            l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.green));
            xAxis += BLOCK_WIDTH;
        }

        // blue
        for (int i = 0; i < 2; i++) {
            l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.blue));
            xAxis += BLOCK_WIDTH;
        }

        // pink
        for (int i = 0; i < 2; i++) {
            l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.pink));
            xAxis += BLOCK_WIDTH;
        }

        // red blocks
        l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), BLOCK_WIDTH, BLOCK_HEIGHT, Color.cyan));
        xAxis += BLOCK_WIDTH;

        l.add(new Block(new Point(xAxis, BLOCKS_Y_AXIS), (BLOCK_WIDTH + CONST), BLOCK_HEIGHT, Color.cyan));
        xAxis += BLOCK_WIDTH + CONST;

        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public Point paddleStartingPoint() {
        return new Point(PADDLE_X, PADDLE_Y);
    }
}


