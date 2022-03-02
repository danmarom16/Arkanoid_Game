// ID: 208995035
package levels;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class representing game's 2nd level's (Wide Easy) background.
 */
public class WideEasyBackground extends Background {

    private static final int X = 120;
    private static final int Y = 100;

    @Override
    public void drawOn(DrawSurface d) {

        Color sun1 = new Color(248, 237, 179);
        Color sun2 = new Color(236, 186, 64);
        Color sun3 = new Color(251, 194, 24);

        // background
        d.setColor(Color.lightGray.brighter());
        d.fillRectangle(0, 0, 800, 600);
        int lineRightX = 20;
        //lines
        for (int i = 0; i < 100; i++) {
            d.setColor(sun1);
            d.drawLine(X, Y, lineRightX, 200);
            lineRightX += 7;
        }
        //sun
        d.setColor(sun1);
        d.fillCircle(X, Y,  48);
        d.setColor(sun2);
        d.fillCircle(X, Y,  38);
        d.setColor(sun3);
        d.fillCircle(X, Y,  30);
    }

}


