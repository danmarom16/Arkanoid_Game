// ID: 208995035
package sprites;

import biuoop.DrawSurface;
import levels.GameLevel;

/**
 * An object that lives inside a game.
 * The sprites.Sprite interface allows us to handle all objects that implement sprites.Sprite in a simple way.
 * The sprites.Sprite interface is implemented by collidables.Block, collidables.Ball and paddle.
 * The sprites.Sprite interface has a draw on method and timePassed method.
 */
public interface Sprite {

    /**
     * Draws the sprites.Sprite on the surface.
     *
     * @param d is the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies all sprites.Sprite that time has passed.
     */
    void timePassed();

    /**
     * Adds sprite to game.
     *
     * @param gameLevel is the game to add sprite to.
     */
    void addToGame(GameLevel gameLevel);
}
