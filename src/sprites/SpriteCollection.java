// ID: 208995035
package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds and manage all game sprites.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Constructor for sprites.Sprite Collection lists.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * Adds a sprite to the sprites.Sprite Collection List.
     *
     * @param s is the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls time passed on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites1 = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : sprites1) {
            s.timePassed();
        }
    }

    /**
     * Call Draw On on all sprites.
     *
     * @param d is the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Returns the sprites list.
     *
     * @return sprite list.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Removes sprite from the list.
     *
     * @param s is the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}
