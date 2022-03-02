// ID: 208995035
package animation;

import biuoop.DrawSurface;

/**
 * A class representing a pause screen.
 */
public class PauseScreen implements Animation {
    //members
    private boolean stop;

    /**
     * Constructor for PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
