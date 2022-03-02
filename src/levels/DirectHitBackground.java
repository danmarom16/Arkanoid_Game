// ID: 208995035
package levels;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class representing game's level 1's (DirectHit) background.
 */
public class DirectHitBackground extends Background {

    @Override
    public void drawOn(DrawSurface d) {

        // background
        d.setColor(Color.black);
        d.fillRectangle(0, 0,  800,  600);

        // bigCircle
        d.setColor(Color.blue);
        d.drawCircle(422, 165, 130);

        // smallerCircle
        d.drawCircle(422, 165, 100);

        // smallerCircle
        d.drawCircle(422, 165, 70);

        // draw crossed lines
        d.drawLine(250, 170, 600, 170);
        d.drawLine(425, 20,  425, 300);
    }
}
