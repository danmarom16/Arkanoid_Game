// ID: 208995035
package geometry;

import biuoop.DrawSurface;
import gameutils.Velocity;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A frame of type rectangle.
 */
public class Rectangle {

    // each rectangle consist 4 line segments -> therefore const.
    private static final int NUM_OF_LINES = 4;

    private ArrayList<Line> rectangleLines;
    private Line leftLine;
    private Line upperLine;
    private Line rightLine;
    private Line lowerLine;

    // members
    private Point upperLeft;
    private double height;
    private double width;
    private Color color;

    /**
     * Constructor for geometry.Rectangle type Frame.
     *
     * @param upperLeft is the coordinate of the upper left point of the rectangle.
     * @param height    is the height of the rectangle.
     * @param width     is the width of the rectangle.
     * @param color is the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.color = color;
        this.setToLines();
    }

    /**
     * Returns the upperLeft geometry.Point of the rectangle.
     *
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns X-Coordinate of upperLeftPoint.
     *
     * @return X-Coordinate of upperLeftPoint.
     */
    public double getX() {
        return this.getUpperLeft().getX();
    }

    /**
     * Returns Y-Coordinate of upperLeftPoint.
     *
     * @return Y-Coordinate of upperLeftPoint.
     */
    public double getY() {
        return this.getUpperLeft().getY();
    }

    /**
     * Returns the height of the rectangle frame.
     *
     * @return Height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the Width of the rectangle frame.
     *
     * @return Width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return Color of rectangle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return left line of the rectangle
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * Returns the upper line of the rectangle.
     *
     * @return upper line of the rectangle
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return right line of the rectangle
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * Returns the lower line of the rectangle.
     *
     * @return lower line of the rectangle
     */
    public Line getLowerLine() {
        return this.lowerLine;
    }

    /**
     * Checks if a rectangle is intersecting with a given line.
     *
     * @param line line to check with.
     * @return true if intersecting, else false.
     */
    public boolean isIntersecting(Line line) {
        return this.intersectionPoints(line) != null;
    }

    /**
     * Breaks down rectangle to 4 lines : left line, upper line, right line, lower line.
     */
    public void setToLines() {
        rectangleLines = new ArrayList<>();
        Point upperRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY());
        Point lowerRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        Point lowerLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        // left geometry.Line
        this.leftLine = new Line(upperLeft, lowerLeft);
        rectangleLines.add(this.leftLine);
        // upper line
        this.upperLine = new Line(upperLeft, upperRight);
        rectangleLines.add(this.upperLine);
        // right line
        this.rightLine = new Line(upperRight, lowerRight);
        rectangleLines.add(this.rightLine);
        // lower line
        this.lowerLine = new Line(lowerLeft, lowerRight);
        rectangleLines.add(this.lowerLine);
    }


    /**
     * Draws the line of the rectangle.
     *
     * @param surface is the surface to draw on.
     */
    public void drawRectLines(DrawSurface surface) {
        for (Line l : rectangleLines) {
            surface.setColor(Color.BLACK);
            surface.drawLine((int) l.getFirst().getX(), (int) l.getFirst().getY(),
                    (int) l.getSecond().getX(), (int) l.getSecond().getY());
        }
    }

    /**
     * Draws the rectangle frame on a given surface.
     *
     * @param surface is the surface to draw om.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        this.drawRectLines(surface);
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line is the line to check with.
     * @return List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // instantiate intersection point list.
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        // for each line of the rectangle, checks if the given line is intersecting with it.
        for (Line luli : this.rectangleLines) {
            Point intersection = luli.intersectionWith(line);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        if (intersectionPoints.toArray().length != 0) {
            return intersectionPoints;
        } else {
            return null;
        }
    }

    /**
     * Sets up new upper left point for rectangle.
     *
     * @param v is the velocity to apply on rectangle.
     */
    public void setUpperLeft(Velocity v) {
        this.upperLeft = v.applyToPoint(this.upperLeft);
    }
}
