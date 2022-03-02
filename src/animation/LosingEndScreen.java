// ID: 208995035
package animation;

import biuoop.DrawSurface;
import gameutils.Counter;
import java.awt.Color;

/**
 * A class representing the Losing End screen of the game.
 */
public class LosingEndScreen implements Animation {
    private boolean stop;
    private Counter finalScore;

    /**
     * Constructor.
     * @param finalScore is the final score of the game.
     */
    public LosingEndScreen(Counter finalScore) {
        this.stop = false;
        this.finalScore = finalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.black);
        d.drawText(200, 200, "Game Over - Your score is: " + this.finalScore.getValue(), 32);

        d.setColor(Color.black);
        d.drawCircle(d.getWidth() / 2, 400, 150);
        d.drawCircle(d.getWidth() / 2 - 60, 330, 30);
        d.drawCircle(d.getWidth() / 2 + 60, 330, 30);
        d.setColor(Color.white);
        d.fillCircle(d.getWidth() / 2 - 60, 330, 30);
        d.fillCircle(d.getWidth() / 2 + 60, 330, 30);
        d.setColor(Color.black);
        d.fillCircle(d.getWidth() / 2 - 75, 333, 10);
        d.fillCircle(d.getWidth() / 2 + 75, 343, 10);

        d.fillCircle(d.getWidth() / 2, 480, 50);
        d.setColor(Color.red);
        d.fillCircle(d.getWidth() / 2, 480, 45);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
