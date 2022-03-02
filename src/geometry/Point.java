// ID: 208995035
package geometry;

/**
 * @author Dan Marom.
 * A geometry.Point class that reprasent Points in 2D dimension.
 */
public class Point {
    // members.
    private double x;
    private double y;

    /**
     * A Constructor for for 2D point.
     *
     * @param x is the value of geometry.Point in X-axis.
     * @param y is the value of geometry.Point in Y-axis.
     */
    public Point(double x, double y) {
        //create new point
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between 2 points.
     *
     * @param other is the point to calculate the distance with.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        // calculate the distance on X-Axis.
        double xAxisDistance = Math.abs(Math.pow(Math.abs(this.x - other.x), 2));
        // calculate the distance in Y-Axis.
        double yAxisDistance = Math.abs(Math.pow(Math.abs(this.y - other.y), 2));
        // sum of both X-Axis distance and Y-Axis distance.
        double sumOfDistance = xAxisDistance + yAxisDistance;
        // returns the root of the sum of both distances.
        return (Math.sqrt(sumOfDistance));
    }

    /**
     * Checks if 2 points are equal.
     *
     * @param other point to compare with.
     * @return True for equality, else False.
     */
    boolean equals(Point other) {
        // store a very small number.
        double epsilon = Math.pow(10, -10);

        /*
         this checks if a-b is closer to 0 than epsilon, if true,
         then a equals b.
         */
        return (Math.abs(this.x - other.x) < epsilon)
                && (Math.abs(this.y - other.y) < epsilon);
    }

    /**
     * Return X-Axis value of point.
     *
     * @return x of point to user.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return Y-Axis value of point.
     *
     * @return y of point to user.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Checks if a point is on a given geometry.Line.
     * Let's mark point as C.
     * If distance(Start->C) + distance(C->End) = distance (Start -> End) ==> the point is on the line.
     *
     * @param line is the line to check with.
     * @return True if on line, else false.
     */
    public boolean belongsTo(Line line) {
        Point start = line.getStart();
        Point end = line.getEnd();
        return Math.abs(this.distance(start) + this.distance(end)) - (start.distance(end)) < 0.001;
    }

    /**
     * Setter for x coordinate of point.
     *
     * @param xVal is the x to assign to our x-coordinate.
     */
    public void setX(double xVal) {
        this.x = xVal;
    }

    /**
     * Setter for the y coordinate of the point.
     *
     * @param yVal is the y coordinate of point.
     */
    public void setY(double yVal) {
        this.y = yVal;
    }

    /**
     * Converts point to string.
     *
     * @return string.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
