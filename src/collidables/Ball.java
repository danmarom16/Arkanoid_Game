// ID: 208995035
package collidables;

import biuoop.DrawSurface;
import levels.GameLevel;
import gameutils.GameEnvironment;
import gameutils.Velocity;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;

/**
 * A ball class representing ball (circle) in 2D dimension.
 */
public class Ball implements Sprite {

    // members that initialized by constructor.
    private Point center;
    private double radius;
    private java.awt.Color color;
    private Velocity velocity;

    // hold reference of game environment.
    private GameEnvironment gameEnvironment;

    // constants.
    private static final double DEF_RAD = 20;
    private static final double MIN_SPEED = 1;
    private static final int CHANGE_DIR = -1;

    /**
     * A constructor to create collidables.Ball with point and radius.
     *
     * @param loc is the middle point of ball.
     * @param rad is the radius of the ball.
     * @param col is the color of the ball.
     */
    public Ball(Point loc, double rad, java.awt.Color col) {
        this.center = loc;
        this.radius = rad;
        this.color = col;
        if (rad <= 0) {
            this.radius = DEF_RAD;
        }
        this.velocity = new Velocity(MIN_SPEED, MIN_SPEED);
    }

    /**
     * A constructor to create collidables.Ball with X and Y values, and radius.
     *
     * @param x is the X-Axis of the point.
     * @param y is the Y-Axis of the point.
     * @param r is the radius of the ball.
     * @param c is the color of ball.
     */
    public Ball(double x, double y, double r, java.awt.Color c) {
        this((new Point(x, y)), r, c);
    }

    /**
     * Returns midPoint (location) of the ball.
     *
     * @return location of ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Return X-Axis value of midPoint.
     *
     * @return the X-Axis of midPoint.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Return Y-Axis value of midPoint.
     *
     * @return the Y-Axis of midPoint.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Return the Color of the ball.
     *
     * @return Color of ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Return the size of the ball radius.
     *
     * @return size of ball.
     */
    public int getRadius() {
        return (int) (radius);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return velocity of ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Default function to set velocity of ball.
     *
     * @param v is the velocity to implement.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * Set velocity of ball.
     *
     * @param dx is the x value of velocity.
     * @param dy is the y value of velocity.s
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * Initiate ball game environmant matching the given frame.
     *
     * @param ge is the given game environment.
     */
    public void initiateBallGameEnvironmnet(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }

    /**
     * Moves the ball one step towards the end of it's trajectory.
     * If no collidables.Collidable is on it's path - jumps to end of trajectory.
     * Else jumps a radius gap from the end.
     */
    public void moveOneStep() {
        Point end = this.velocity.applyToPoint(this.center);
        end = this.velocity.applyToPoint(end);

        // calculate the trajectory from the center of the ball
        Line trajectory = new Line(this.center, end);

        // get closest collision of balls trajectory with existed game collidables.
        CollisionInfo closestCollision = this.gameEnvironment.getClosestCollision(trajectory);

        /*
         if there is no colision - jump to end of trajectory.
         if there where a colision - jump 1 radius back form trajectory's end.
         */
        if (closestCollision == null) {

            // move ball to the end of trajectory.
            this.center = this.velocity.applyToPoint(this.center);
        } else {

            // creates temporary velocity that will send the ball radius back.
            Velocity jumpRadiusBack = Velocity.fromAngleAndSpeed(this.getVelocity().getAngle(),
                    CHANGE_DIR * (this.getRadius()));

            // apply jump radius back to the center of the ball
            this.center = jumpRadiusBack.applyToPoint(closestCollision.collisionPoint());

            closestCollision.collisionObject().inBoundCheck(this.center);

            //notify the hit object (using its hit() method) that a collision occurred.
            this.velocity = closestCollision.collisionObject().hit(this, closestCollision.collisionPoint(),
                    this.getVelocity());

        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle((int) this.getX(), (int) this.getY(), (int) radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), (int) radius);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.initiateBallGameEnvironmnet(g.getGameEnvironment());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Removes ball from game.
     *
     * @param gameLevel is the game to remove ball from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}