// ID: 208995035
package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * An abstract class representing background.
 */
public abstract class Background implements Sprite {


    @Override
    public void drawOn(DrawSurface d) {
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
