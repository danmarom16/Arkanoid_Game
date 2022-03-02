// ID: 208995035
package collidables;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.GameLevel;
import gameutils.GameEnvironment;
import gameutils.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;


/**
 * The paddle of the Arknoid game.
 * The paddle support left and right movement controlled by player.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    private Block paddle;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private Line[] region;
    private int speed;
    private GameLevel gameLevel;


    // movement constants.
    private static final int MOVE_LEFT = -5;
    private static final int MOVE_RIGHT = 5;
    private static final int CHANGE_DIR = -1;

    /**
     * Constructor for collidables.Paddle.
     *
     * @param g   is the gaeme passed to paddle.
     */
    public Paddle(GameLevel g) {
        this.gui = g.getanimationRunner().getGui();
        this.keyboard = g.getanimationRunner().getGui().getKeyboardSensor();
        this.paddle = new Block(g.getLevelInfo().paddleStartingPoint(),
                g.getLevelInfo().paddleWidth(), GameLevel.PADDLE_HEIGHT, Color.ORANGE);
        this.speed = g.getLevelInfo().paddleSpeed();
        this.region = new Line[5];
        this.setPaddleRegions();
        this.v = new Velocity(0, 0);
        this.gameEnvironment = g.getGameEnvironment();
        this.gameLevel = g;
    }

    /**
     * Split paddle into 5 lines each called a region and saves them in an array.
     * region[0] is the most left part.
     * region[1] is the 2nd most left part.
     * region[2] is the middle part.
     * region[3] is the 2nd most right part.
     * region[4] is the most right part.
     */
    private void setPaddleRegions() {

        // paddle rectangle shape
        Rectangle r = this.paddle.getCollisionRectangle();

        // upper left point.
        Point p = r.getUpperLeft();

        // paddle upper line
        Line ul = r.getUpperLine();

        // paddle length
        double pl = r.getWidth();

        // region length
        double rl = pl / 5;

        // adds paddle regions
        this.region[0] = new Line(new Point(p.getX(), p.getY()), new Point(p.getX() + rl, p.getY()));
        this.region[1] = new Line(new Point(p.getX() + rl, p.getY()), new Point((p.getX() + (2 * rl)), p.getY()));
        this.region[2] = new Line(new Point(p.getX() + (2 * rl), p.getY()), new Point((p.getX() + (3 * rl)), p.getY()));
        this.region[3] = new Line(new Point(p.getX() + (3 * rl), p.getY()), new Point((p.getX() + (4 * rl)), p.getY()));
        this.region[4] = new Line(new Point(p.getX() + (4 * rl), p.getY()), new Point((p.getX() + (5 * rl)), p.getY()));

    }

    /**
     * Moves paddle left by resetting its new upper left point.
     */
    public void moveLeft() {
        Rectangle r = this.paddle.getCollisionRectangle();
        Line leftBound = this.gameEnvironment.getLeftBound().getCollisionRectangle().getRightLine();
        Point ul = r.getUpperLeft();
        // if the upper left point is intersecting with left border - don't do anything.
        if (ul.belongsTo(leftBound)) {
            return;
        }
        if (ul.getX() - speed < 20) {
            this.setToLeftBound();
            return;
        }
        v = new Velocity(this.speed * -1, 0);
        r.setUpperLeft(v);
        r.setToLines();
        this.setPaddleRegions();

    }

    /**
     * If paddle passed left border, returns it to the left
     * border limit.
     */
    public void setToLeftBound() {
        this.paddle = new Block(new Point(20, 570),
                this.gameLevel.getLevelInfo().paddleWidth(), GameLevel.PADDLE_HEIGHT, Color.ORANGE);
    }


    /**
     * Moves paddle left by resetting its new upper left point.
     */
    public void moveRight() {
        Rectangle r = this.paddle.getCollisionRectangle();
        Line rightBound = this.gameEnvironment.getRightBound().getCollisionRectangle().getLeftLine();
        Point ur = new Point((int) (r.getX() + r.getWidth()), (int) r.getUpperLeft().getY());
        // if the upper left point is intersecting with left border - don't do anything.
        if (ur.belongsTo(rightBound)) {
            return;
        }
        if (ur.getX() + speed > 780) {
            this.setToRightBound();
            return;
        }
        v = new Velocity(this.speed, 0);
        r.setUpperLeft(v);
        r.setToLines();
        this.setPaddleRegions();
    }

    /**
     * If paddle passed right border, returns it to the right
     * border limit.
     */
    public void setToRightBound() {
        this.paddle = new Block(new Point(780 - this.gameLevel.getLevelInfo().paddleWidth(), 570),
                this.gameLevel.getLevelInfo().paddleWidth(), GameLevel.PADDLE_HEIGHT, Color.ORANGE);
    }

    /**
     * Notify paddle that the time has ended.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }


    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Line leftSide = this.getCollisionRectangle().getLeftLine();
        Line upperSide = this.getCollisionRectangle().getUpperLine();
        Line rightSide = this.getCollisionRectangle().getRightLine();
        Line lowerSide = this.getCollisionRectangle().getLowerLine();

        // if hits region0 change angle in 330 deg.
        if (collisionPoint.belongsTo(region[0])) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }

        // if hits region1 change angle in 300 deg.
        if (collisionPoint.belongsTo(region[1])) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }

        // if hits region2 change verticel speed only..
        if (collisionPoint.belongsTo(region[2])) {
            return new Velocity(currentVelocity.getDx(), CHANGE_DIR * currentVelocity.getDy());
        }
        // if hits region3 change angle in 60 deg.
        if (collisionPoint.belongsTo(region[3])) {
            return Velocity.fromAngleAndSpeed(-330, currentVelocity.getSpeed());
        }

        // if hits region0 change angle in 30 deg.
        if (collisionPoint.belongsTo(region[4])) {
            return Velocity.fromAngleAndSpeed(-300, currentVelocity.getSpeed());
        }

        // if hits sides.
        return new Velocity(CHANGE_DIR * currentVelocity.getDx(), currentVelocity.getDy());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * If point is inside paddle - pops if to the nearest border (left or right).
     *
     * @param p is the point to check if inside the paddle.
     */
    public void inBoundCheck(Point p) {

        // left bound
        double lb = this.getCollisionRectangle().getX();

        // upper bound
        double ub = this.getCollisionRectangle().getY();

        // right bound
        double rb = this.getCollisionRectangle().getX() + this.getCollisionRectangle().getWidth();

        // lower bound
        double lob = this.getCollisionRectangle().getY() + this.getCollisionRectangle().getHeight();

        // x-coordinate of p
        double x = p.getX();
        double y = p.getY();

        // if p inside paddle.
        if ((lb < x && x < rb) && (ub <= y && y < lob)) {
            if (Math.abs(x - lb) < Math.abs(x - rb)) {
                p.setX(lb);
                p.setY(this.getCollisionRectangle().getUpperLeft().getY() - 1);
            } else {
                p.setX(rb);
                p.setY(this.getCollisionRectangle().getUpperLeft().getY() - 1);


            }
        }
    }
}
