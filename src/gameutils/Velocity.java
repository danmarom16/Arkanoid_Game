// ID: 208995035
package gameutils;

import geometry.Point;

/**
 * Represent gameUtils.Velocity of object in 2D dimension.
 */
public class Velocity {

    // members are the change in X and Y Axes.
    private double dx;
    private double dy;

    /**
     * Constructor for gameUtils.Velocity.
     *
     * @param dx is the change in X-Axis.
     * @param dy is the change in Y-Axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies the velocity to a geometry.Point type object.
     *
     * @param p geometry.Point to apply change on.
     * @return geometry.Point with updated values.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Creates velocity given angle and speed by converting them to the change
     * in X-Axis, and the change in Y-Axis using sinus and co-sinus in a
     * right angle triangle.
     *
     * @param angle is the angle given.
     * @param speed is the speed given
     * @return gameUtils.Velocity converted to dx (change in X-Axis) and dy(change in X-Axis).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (Math.sin(Math.toRadians(angle)));
        double dy = (-1) * speed * (Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * Returns the change in X-Axis.
     *
     * @return change in X.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in Y-Axis.
     *
     * @return change in Y.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Returns the angle of the velocity.
     *
     * @return angle.
     */
    public double getAngle() {
        return (Math.toDegrees(Math.atan2(this.getDy(), this.getDx())) + 90);
    }

    /**
     * Returns the speed of the velocity.
     *
     * @return speed
     */
    public double getSpeed() {
        double insideRoot = (Math.pow(dx, 2) + Math.pow(dy, 2));
        return Math.sqrt(insideRoot);
    }
}
