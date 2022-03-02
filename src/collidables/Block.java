// ID: 208995035
package collidables;

import biuoop.DrawSurface;
import levels.GameLevel;
import gameutils.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * collidables.Block class represent block type objects.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private static final int CHANGE_DIR = -1;
    private Rectangle block;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor that initialize block.
     *
     * @param upperLeft is the upper left point.
     * @param width     is the width of the block.
     * @param height    is the height of the block.
     * @param color     is the color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.block = new Rectangle(upperLeft, width, height, color);
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Line leftSide = this.getCollisionRectangle().getLeftLine();
        Line upperSide = this.getCollisionRectangle().getUpperLine();
        Line rightSide = this.getCollisionRectangle().getRightLine();
        Line lowerSide = this.getCollisionRectangle().getLowerLine();
        /*
         Checks if hits left or right side and then changes horizontally.
         Else changes Vertically.
         */
        if ((collisionPoint.belongsTo(rightSide)) || (collisionPoint.belongsTo(leftSide))) {
            return new Velocity((CHANGE_DIR * (currentVelocity.getDx())), currentVelocity.getDy());
        } else {
            return new Velocity(((currentVelocity.getDx())), (CHANGE_DIR * currentVelocity.getDy()));
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.getCollisionRectangle().drawOn(surface);

    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public void inBoundCheck(Point p) { };

    /**
     * Removes block from game.
     *
     * @param gameLevel is the game to remove block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all Block listener that a hit had occurred.
     *
     * @param hitter is the hitter ball.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}

