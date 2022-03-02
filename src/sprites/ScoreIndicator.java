// ID: 208995035
package sprites;

import biuoop.DrawSurface;
import gameutils.Counter;
import levels.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * A class that makes the games score board.
 */
public class ScoreIndicator implements Sprite {

    private Counter gameScore;
    private Rectangle rectangle;
    private GameLevel game;

    /**
     * A constructor for the score indicator block.
     *
     * @param gameLevel is the game to add the score indicator to.
     */
    public ScoreIndicator(GameLevel gameLevel) {
        this.game = gameLevel;
        this.gameScore = gameLevel.getScoreCounter();
        Point p = new geometry.Point(0, 0);
        double guiWidth = gameLevel.getanimationRunner().getGui().getDrawSurface().getWidth();
        this.rectangle = new geometry.Rectangle(p, guiWidth, 20, Color.WHITE);
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
        d.drawText(d.getWidth() / 2 - 50, 17, "Score: " + this.gameScore.getValue(), 15);

        // lives
        d.drawText(50, 17, "Lives: 7", 15);

        // game level
        d.drawText(600, 17, "Game Level: " + this.game.getLevelInfo().levelName(), 15);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
