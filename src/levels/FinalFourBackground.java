// ID: 208995035
package levels;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class representing game's level 4's (Final Four) background.
 */
public class FinalFourBackground extends Background {
    private static final int XCHANGE = 550;
    private static final int YCHANGE = 100;
    private static final int LLINE_SLOPE = 27;
    private static final int LINE_X_JUMP = 8;

    @Override
    public void drawOn(DrawSurface d) {

        // background
        Color c = new Color(8, 138, 198);
        d.setColor(c);
        d.fillRectangle(0, 0, 800, 600);

        int lineX = 100;
        // left cloud lines
        d.setColor(new Color(205, 205, 205));
        for (int i = 0; i < 10; i++) {
            d.drawLine(lineX, 410, lineX - LLINE_SLOPE, 600);
            lineX = lineX + LINE_X_JUMP;
        }
        // right cloud lines
        lineX = 100 + XCHANGE;
        d.setColor(new Color(205, 205, 205));
        for (int i = 0; i < 10; i++) {
            d.drawLine(lineX, 410 + YCHANGE, lineX - LLINE_SLOPE, 600);
            lineX = lineX + LINE_X_JUMP;
        }

        // Left cloud
        d.setColor(new Color(205, 205, 205));
        d.fillCircle(100, 420, 20);
        d.fillCircle(115, 445, 20);
        d.setColor(new Color(186, 185, 185));
        d.fillCircle(130, 410, 25);
        d.setColor(new Color(156, 156, 156));
        d.fillCircle(160, 430, 25);
        d.fillCircle(145, 445, 20);
        // right cloud
        d.setColor(new Color(205, 205, 205));
        d.fillCircle(XCHANGE + 100, YCHANGE + 400, 20);
        d.fillCircle(XCHANGE + 115, YCHANGE + 425, 20);
        d.setColor(new Color(186, 185, 185));
        d.fillCircle(XCHANGE + 130, YCHANGE + 390, 25);
        d.setColor(new Color(156, 156, 156));
        d.fillCircle(XCHANGE + 160, YCHANGE + 410, 25);
        d.fillCircle(XCHANGE + 145, YCHANGE + 425, 20);

    }
}
