// ID: 208995035
package collidables;

import geometry.Point;

/**
 * Composed of collision point and collision object.
 * Returns the collision point and collision object.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor for collision info.
     *
     * @param p is the point to add.
     * @param c is the collidable to add.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    /**
     * The point at which the collision occurs.
     *
     * @return the collision.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The collidable object involved in the collision.
     *
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
