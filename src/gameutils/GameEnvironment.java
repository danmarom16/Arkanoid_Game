// ID: 208995035
package gameutils;

import biuoop.GUI;
import collidables.Block;
import collidables.Collidable;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the Arknoid game environment.
 * Contains all collidable objects in the game.
 */
public class GameEnvironment {

    private static final int EMPTY_LIST = 0;

    private List<Collidable> gameCollidables;
    private SpriteCollection gameSprites;
    private static final int CONST = 19;


    /**
     * Constructor that initiate gameCollidables list, and adds 4 block that bound
     * the given frame.
     *
     * @param gui is the given gui.
     * @param sprites is the games sprite list.
     */
    public GameEnvironment(GUI gui, SpriteCollection sprites) {


        this.gameCollidables = new ArrayList<>();
        this.gameSprites = sprites;

        // variables created to improve readability.
        int frameHeight = gui.getDrawSurface().getHeight();
        int frameWidth = gui.getDrawSurface().getWidth();
        Point frameUpperLeftCorner = new Point(0, CONST);
        Point frameUpperRightCorner = new Point(frameWidth - CONST, 0);

        // create 4 boundaries of the game environment.
        Collidable leftBound = new Block(frameUpperLeftCorner, CONST, frameHeight, Color.DARK_GRAY);
        Collidable upperBound = new Block(frameUpperLeftCorner, frameWidth, CONST, Color.DARK_GRAY);
        Collidable rightBound = new Block(frameUpperRightCorner, CONST, frameHeight, Color.DARK_GRAY);


        // adds them to the game environment as Collidables and Sprites.

        // left Bound.
        addCollidable(leftBound);
        this.gameSprites.addSprite((Sprite) leftBound);

        // upper Bound.
        addCollidable(upperBound);
        this.gameSprites.addSprite((Sprite) upperBound);

        // right Bound.
        addCollidable(rightBound);
        this.gameSprites.addSprite((Sprite) rightBound);

        // lower bound is the Death region - therfore not initialized here.
    }

    /**
     * Adds collidable object to the environment.
     *
     * @param c is the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.gameCollidables.add(c);
    }

    /**
     * Get the closest colision of a given trajectory's start point, from all collision with the
     * current collidables in the game environment.
     * If no such collision occur, return null.
     *
     * @param trajectory is the given trajectory to check with.
     * @return closest collision to the start of the trajectory if exist. if not returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // a pointer that will point to the closest collision.
        Collidable colusionObject = null;
        Point closetCollisionPoint = null;
        double minDistance = 2000;
        for (Collidable c : gameCollidables) {
            Point currentPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (currentPoint != null) {
                if (currentPoint.distance(trajectory.getFirst()) < minDistance) {
                    closetCollisionPoint = currentPoint;
                    minDistance = currentPoint.distance(trajectory.getFirst());
                    colusionObject = c;
                }
            }
        }
        if (closetCollisionPoint == null) {
            return null;
        }
        return new CollisionInfo(closetCollisionPoint, colusionObject);
    }

    /**
     * Returns current list of existed collidables in the game.
     *
     * @return collidables list.
     */
    public List<Collidable> getCollidables() {
        return this.gameCollidables;
    }

    /**
     * Returns the current Sprites in the game.
     *
     * @return Sprites existed in the game environment.
     */
    public SpriteCollection getSpriteCollection() {
        return this.gameSprites;
    }

    /**
     * Returns the left bound of the game environment.
     *
     * @return left bound of the game environment.
     */
    public Collidable getLeftBound() {
        return this.gameCollidables.get(0);
    }

    /**
     * Returns the right bound of the game environment.
     *
     * @return right bound of the game environment.
     */
    public Collidable getRightBound() {
        return this.gameCollidables.get(2);
    }

    /**
     * Removes collidable from game.
     *
     * @param c is the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        if (this.getCollidables().contains(c)) {
            this.gameCollidables.remove(c);
        }
    }

    /**
     * Removes sprite from game.
     *
     * @param s is the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        if (this.getSpriteCollection().getSprites().contains(s)) {
            this.gameSprites.removeSprite(s);
        }
    }

}
