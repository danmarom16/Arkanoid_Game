// ID: 208995035
package levels;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class representing game's level 3's (Green3) background.
 */
public class Green3Background extends Background {

    @Override
    public void drawOn(DrawSurface d) {

        //green screen
        d.setColor(new Color(42, 129, 21));
        d.fillRectangle(0, 0, 800, 600);

        //long antenna
        d.setColor(new Color(62, 62, 62));
        d.fillRectangle(108, 200, 10, 500);

        // bottom of antenna
        d.setColor(new Color(45, 50, 49));
        d.fillRectangle(97, 400, 30, 50);

        //building
        d.setColor(Color.black);
        d.fillRectangle(60, 450, 100, 600);
        d.setColor(Color.WHITE);
        d.fillRectangle(70, 460, 80, 600);
        int x = 80;
        d.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            d.fillRectangle(x, 450, 7, 600);
            x += 17;
        }
        int y = 485;
        for (int i = 0; i < 4; i++) {
            d.fillRectangle(60, y, 100, 5);
            y += 30;
        }

        // top of antena
        d.setColor(new Color(255, 204, 71));
        d.fillCircle(113, 190, 10);

        d.setColor(new Color(255, 86, 44));
        d.fillCircle(113, 190, 7);

        d.setColor(Color.white);
        d.fillCircle(113, 190, 3);
    }
}
