// ID: 208995035
package geometry;

/**
 * A geometry.Line class representing line segments in 2D dimension.
 */
public class Line {

    // start and end points
    private Point start;
    private Point end;

    private Point first;
    private Point second;

    private static final int SIGN_REVERSAL = -1;

    /**
     * A default constructor for geometry.Line with 2 Points.
     *
     * @param p1 1st point of line.
     * @param p2 2nd point of line.
     */
    public Line(Point p1, Point p2) {
        this.first = p1;
        this.second = p2;
        if (p1.getX() < p2.getX()) {
            this.start = p1;
            this.end = p2;
        } else if (p1.getX() == p1.getX()) {
            if (p1.getY() < p2.getY()) {
                this.start = p1;
                this.end = p2;
            } else {
                this.start = p2;
                this.end = p1;
            }
        } else {
            this.start = p2;
            this.end = p1;
        }
    }

    /**
     * A constructor for geometry.Line with separate x and y values.
     * Calls default constructor with values converted to Points.
     *
     * @param x1 is the X-Axis value of start point.
     * @param y1 is the Y-Axis value of start point.
     * @param x2 is the X-Axis value of end point.
     * @param y2 is the Y-Axis value of end point.
     */
    Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), (new Point(x2, y2)));
    }

    /**
     * Returns the starting geometry.Point of the line.
     *
     * @return Start geometry.Point.
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Returns the ending geometry.Point of the line.
     *
     * @return End geometry.Point.
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Returns the distance between Start geometry.Point and End geometry.Point.
     *
     * @return Returns the Length of the geometry.Line.
     */
    public Point getFirst() {
        return this.first;
    }

    /**
     * Returns the second point.
     *
     * @return the second point.
     */
    public Point getSecond() {
        return this.second;
    }

    /**
     * Returns the length of the line.
     *
     * @return length of the line
     */
    public double getLength() {
        return this.start.distance(this.end);
    }

    /**
     * Checks if 2 Lines are equal.
     *
     * @param other is the line to compare with
     * @return True if equal, else False.
     */
    public boolean equals(Line other) {
        // store a very small number.
        double epsilon = Math.pow(10, -10);

        /*
         this checks if a-b is closer to 0 than epsilon, if true,
         then a equals b.
         */
        return (Math.abs(this.start.getY() - other.start.getY()) < epsilon)
                && (Math.abs(this.start.getX() - other.start.getX()) < epsilon)
                && (Math.abs(this.end.getY() - other.end.getY()) < epsilon)
                && (Math.abs(this.end.getX() - other.end.getX()) < epsilon);
    }

    /**
     * returns the middle point of the line.
     *
     * @return middle point.
     */
    public Point middle() {
        double xMiddle = ((Double.sum(this.start.getX(),
                this.end.getX()) / 2));
        double yMiddle = ((Double.sum(this.start.getY(),
                this.end.getY()) / 2));
        return new Point(xMiddle, yMiddle);
    }

    /**
     * Checks if 2 line segments are intersecting.
     *
     * @param other for to compare with.
     * @return intersection geometry.Point if intersecting, else null.
     */
    public Point intersectionWith(Line other) {
        if ((!(this.equals(other))) && ((this.start.equals(other.end)))) {
            return this.start;
        }
        if (((!(this.equals(other))) && (this.end.equals(other.start)))) {
            return this.end;
        }
        /*
         We will use the standard form of Linear geometry.Line for both lines,
         represented as: Ax+By=C.
         */
        // A = y2 - y1.
        double a1 = this.end.getY() - this.start.getY();
        double a2 = other.end.getY() - other.start.getY();
        // B = x1 - x2.
        double b1 = this.start.getX() - this.end.getX();
        double b2 = other.start.getX() - other.end.getX();
        // By this linear presentation A should be positive.
        if (this.start.getY() > this.end.getY()) {
            a1 = SIGN_REVERSAL * a1;
            b1 = SIGN_REVERSAL * b1;
        }
        if (other.start.getY() > other.end.getY()) {
            a2 = SIGN_REVERSAL * a2;
            b2 = SIGN_REVERSAL * b2;
        }
        // C = Ax1 + By2
        double c1 = ((a1 * this.start.getX()) + (b1 * this.start.getY()));
        double c2 = ((a2 * other.start.getX()) + (b2 * other.start.getY()));
        /*
         Denominator Will be the same for X-coordinate and Y-coordinate,
         hence the use of a variable.
         */
        double denominator = ((a1 * b2) - (a2 * b1));
         /*
          if the Slop of both lines is equivalent that means that
          they are parallel or co-linear lines.
          If they are parallel - they won't intersect.
          If they ar co-linear - they have infinite intersection points.
          */
        if (denominator == 0) {
            return null;
        }
        // defined for readability of the program.
        double xIntersection = (((c1 * b2) - (c2 * b1)) / denominator);
        double yIntersection = (((a1 * c2) - (a2 * c1)) / denominator);
        Point intersectPoint = new Point(xIntersection, yIntersection);
        if ((intersectPoint.belongsTo(this)) && (intersectPoint.belongsTo(other))) {
            return intersectPoint;
        }
        return null;
    }

    /**
     * Boolean function to check if 2 lines intersect.
     *
     * @param other is the line to check with.
     * @return True if intersecting, else return false.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Returns the closest intersection point of line with a given rectangle.
     *
     * @param rect is the rectangle to check intersection with
     * @return the closest point if exist, if not returns null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestToStart = null;
        // if line is intersecting with the given rect
        if (rect.isIntersecting(this)) {
            double minDistance = 2000;
            // saves the intersection point int an Array list.
            java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
            // intersection point will be closer or equal to end.
            // loop to find the closest point to start point.
            for (Point point : intersectionPoints) {
                if (point.distance(this.getFirst()) < minDistance) {
                    minDistance = point.distance(this.getFirst());
                    closestToStart = point;
                }
            }
        }
        return closestToStart;

    }

    /**
     * Tells if a line intersect with rectangle.
     *
     * @param rect is the rectangle to check with.
     * @return true if intersecting, else false.
     */
    public boolean isIntersectingWithRect(Rectangle rect) {
        return this.closestIntersectionToStartOfLine(rect) != null;
    }

    /**
     * @return A string that represents a line as follows: "(x1, y1) -> (x2, y2)".
     */
    public String toString() {
        return (this.start.toString() + " -> " + this.end.toString());
    }
}


