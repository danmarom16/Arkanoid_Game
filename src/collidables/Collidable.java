// ID: 208995035
package collidables;

import gameutils.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * An object that objects can colide with.
 * The collidables.Collidable interface is implemented by collidables.Block and collidables.Paddle.
 * The collidables.Collidable interface provides a hit method that returns new velocity after hitting it
 * and a getter for the shape.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint is the collision point.
     * @param currentVelocity is the current object velocity.
     * @param hitter is the hitter ball.
     * @return new gameUtils.Velocity after hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Checks if the a point is inside the collidable.
     * If it does, it reset the point to the nearest border left of right.
     * (Made to prevent ball to get into paddle)
     *
     * @param p is the point to check with.
     */
    void inBoundCheck(Point p);
}

