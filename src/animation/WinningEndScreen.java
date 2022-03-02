// ID: 208995035
package animation;

import biuoop.DrawSurface;
import gameutils.Counter;
import java.awt.Color;

/**
 * A class representing the Winning screen of the game.
 */
public class WinningEndScreen implements Animation {

    private boolean stop;
    private Counter finalScore;

    /**
     * Constructor.
     *
     * @param finalScore is the final score of the game.
     */
    public WinningEndScreen(Counter finalScore) {
        this.stop = false;
        this.finalScore = finalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.black);
        d.drawCircle(d.getWidth() / 2,  400,  150);
        d.drawCircle(d.getWidth() / 2 - 60, 330, 30);
        d.drawCircle(d.getWidth() / 2 + 60,  330, 30);
        d.setColor(Color.white);
        d.fillCircle(d.getWidth() / 2 - 60, 330, 30);
        d.fillCircle(d.getWidth() / 2 + 60, 330, 30);
        d.setColor(Color.black);
        d.fillCircle(d.getWidth() / 2 - 60, 330, 10);
        d.fillCircle(d.getWidth() / 2 + 60, 333, 10);

        d.fillOval(d.getWidth() / 2 - 40, 480, 100, 20);
        d.setColor(Color.green);
        d.fillOval(d.getWidth() / 2 - 45, 475, 90, 20);

        d.setColor(Color.white);
        d.drawText(200, 200, "You Win! - Your score is: " + this.finalScore.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
