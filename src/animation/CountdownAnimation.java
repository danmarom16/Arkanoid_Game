// ID: 208995035
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * A class representing the CountdownAnimation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int currentCall;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;


    /**
     * Constructor.
     *
     * @param numOfSeconds is the number of seconds to wait.
     * @param countFrom is the start of the count.
     * @param gameScreen is the current game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.sleeper = new Sleeper();
        this.stop = false;

        // to indicate if it's the 1st time this method has been called - for doOneFrame.
        this.currentCall = 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 100, String.valueOf(countFrom), 100);
        if (this.countFrom <= 0) {
            this.stop = true;
        }

        // don't sleep in 1st call
        if (currentCall != 0) {
            sleeper.sleepFor((long) (0.667 * 1000));
        }
        this.reduceCount();
        this.currentCall += 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Reduces count by 1.
     */
    public void reduceCount() {
        this.countFrom -= 1;
        this.numOfSeconds -= 1;
    }

}
