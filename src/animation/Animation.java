// ID: 208995035
package animation;

import biuoop.DrawSurface;

/**
 * An interface representing animation.
 */
public interface Animation {

    /**
     * Does one frame of action according to
     * the animation its working on.
     *
     * @param d is the surface to do the frame om.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Incicate if the animation should stop.
     *
     * @return false if animation need to continue, else true.
     */
    boolean shouldStop();
}
