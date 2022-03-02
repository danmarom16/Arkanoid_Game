// ID: 208995035
package levels;

import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidables.Ball;
import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;
import gameutils.Counter;
import gameutils.GameEnvironment;
import gameutils.Velocity;
import listeners.ScoreTrackingListener;
import listeners.HitListener;
import listeners.BlockRemover;
import listeners.BallRemover;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import geometry.Point;

/**
 * Class representing the Arkanoid game.
 * Adds collidables.Collidable to the game, initialize it and run it.
 *
 */
public class GameLevel implements Animation {

    //const for creating blocks.
    private static final int NUM_OF_BLOCKS = 12;
    private static final int BLOCK_ROWS = 6;
    public static final int DEFAULT_SPEED = 6;
    public static final int DEFAULT_ANGLE = 120;

    // const for block.
    public static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCKS_STARTING_X = 730;
    private static final int BLOCKS_STARTING_Y = 100;

    private static final int BALL_STARTING_X = 410;
    private static final int BALL_STARTING_Y = 500;

    // global const of game frames.
    public static final int MIDDLE_X = 365;
    public static final int MIDDLE_Y = 280;

    // global const for paddle -
    public static final int PADDLE_Y = 560;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_WIDTH = 120;
    public static final int BALL_RAD = 5;

    public static final int FPS = 60;


    // members.
    private Sprite backGround;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter remainingBlocksCounter = new Counter();
    private Counter remainingBallsCounter = new Counter();
    private Counter scoreCounter;
    private Random r = new Random();
    private AnimationRunner animationRunner;
    private boolean isRunning;

    private LevelInformation levelInfo;

    /**
     * Constructor.
     *
     * @param levelInfo is the information of the running level.
     * @param ar is the animation runner.
     * @param currentScore is the current score.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, Counter currentScore) {
        this.sprites = new SpriteCollection();
        this.levelInfo = levelInfo;
        this.animationRunner = ar;
        this.scoreCounter = currentScore;
    }


    /**
     * Adds a new coliidable to the game  using delegation on gameUtils.Game Environment.
     *
     * @param c us the collidable to add.
     */

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a new sprites.Sprite to the game using delegation on sprites.Sprite interface.
     *
     * @param s is the sprite to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize the game.
     * @param g is the Gui of the game.
     */
    public void initialize(GUI g) {
        this.animationRunner = new AnimationRunner(FPS, g);
        // initialize all games objects.
        this.initGameObj();

        // initialize listeners.
        HitListener br = new BlockRemover(this, this.remainingBlocksCounter);
        HitListener ballr = new BallRemover(this, remainingBallsCounter);
        HitListener sc = new ScoreTrackingListener(this.scoreCounter);

        // generate score
        this.generateScore();

        // generate Paddle
        this.generatePaddle();

        // generate starting blocks
        this.generateBlocks(br, sc, this.levelInfo.blocks());

        // generate 3 balls.
        for (int i = 0; i < this.getLevelInfo().numberOfBalls(); i++) {
            this.generateBalls(this.levelInfo.initialBallVelocities().get(i));
        }

        // Generate death region.
        this.generateDeathReg(ballr);

    }

    /**
     * Runs the level.
     */
    public void run() {
        this.animationRunner.run(new CountdownAnimation(3, 3, this.sprites));
        this.isRunning = true;
        this.animationRunner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.animationRunner.getGui().getKeyboardSensor().isPressed("p")) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui().getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY,  new PauseScreen()));
        }
        /*
        isOver will return True if ball counter or block counter == 0, hence
        isRunning will be false.
        Otherwise isOver will return False, hence isRunning will be true.
         */
        this.isRunning = !this.isOver();
    }

    @Override
    public boolean shouldStop() {
        return !this.isRunning;
    }



    /**
     * Checks if game is over.
     *
     * @return true if game is not over, False if over.
     */
    public boolean isOver() {
        if (this.getRemainingBlocksCounter().getValue() == 0) {
            return true;
        }
        if (this.getRemainingBallsCounter().getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Increase game score counter when a lvl up occurred.
     */
    public void levelUp() {
        this.getScoreCounter().increase(100);
    }
    /**
     * Initialize game objects.
     */
    public void initGameObj() {
        this.backGround = this.levelInfo.getBackground();
        this.sprites.addSprite(this.backGround);
        this.environment = new GameEnvironment(animationRunner.getGui(), this.sprites);

    }

    /**
     * Generate game's score.
     */
    public void generateScore() {
        Sprite score = new ScoreIndicator(this);
        score.addToGame(this);
    }

    /**
     * Generate game's paddle.
     */
    public void generatePaddle() {
        this.paddle = new Paddle(this);
        paddle.addToGame(this);
    }
    /**
     * Generate games death region.
     *
     * @param ballr is the Ball Remover Listener.
     */
    public void generateDeathReg(HitListener ballr) {
        Block deathRegion = new Block(new Point(0, 600), animationRunner.getGui().getDrawSurface().getWidth(),
                20, Color.black);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballr);
    }
    /**
     * Generate Ball.
     *
     * @param v is level balls velocity.
     */
    public void generateBalls(Velocity v) {
        Ball b = new Ball(new Point(400, 550), BALL_RAD, Color.white);
        b.setVelocity(v);
        b.addToGame(this);
        this.getRemainingBallsCounter().increase(1);
    }

    /**
     * Generate simple starting blocks.
     *
     * @param br is the BallRemover listener.
     * @param sc is the ScoreIndicator Listener.
     * @param blocks is the list of blocks to generate.
     */
    public void generateBlocks(HitListener br, HitListener sc, List<Block> blocks) {
        for (Block block : blocks) {
            block.addHitListener(br);
            block.addHitListener(sc);
            block.addToGame(this);
            this.remainingBlocksCounter.increase(1);
        }
    }

    /**
     * Returns the environment of the game.
     *
     * @return game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Removes sprite from game.
     *
     * @param s is the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.environment.removeSprite(s);
    }

    /**
     * Get the counter of remaining game blocks.
     *
     * @return counter of remaining block.
     */
    public Counter getRemainingBlocksCounter() {
        return remainingBlocksCounter;
    }

    /**
     * Get the counter of remaining game balls.
     *
     * @return counter of remaining game balls.
     */
    public Counter getRemainingBallsCounter() {
        return remainingBallsCounter;
    }

    /**
     * Get the counter of remaining game score.
     *
     * @return game score counter.
     */
    public Counter getScoreCounter() {
        return scoreCounter;
    }

    /**
     * Returns the running level's information.
     *
     * @return running level's information
     */
    public LevelInformation getLevelInfo() {
        return this.levelInfo;
    }

    /**
     * Returns the animation runner.
     *
     * @return animation runner.
     */
    public AnimationRunner getanimationRunner() {
        return this.animationRunner;
    }
    /**
     * Removes collidable from game.
     *
     * @param c is the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
        }

}
