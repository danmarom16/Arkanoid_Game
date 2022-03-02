// ID: 208995035
package levels;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.LosingEndScreen;
import animation.WinningEndScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameutils.Counter;
import java.util.List;

/**
 * A class that is responsible for the flow of the game - runs game levels.
 */
public class GameFlow {

    private Counter gameScore;
    private AnimationRunner animationRunner;
    private GUI gui;

    /**
     * Constructor.
     * @param ar is the animation runner.
     * @param g is the Gui.
     */
    public GameFlow(AnimationRunner ar, GUI g) {
        this.animationRunner = ar;
        this.gameScore = new Counter();
        this.gui = g;
    }

    /**
     * Runs the given levels.
     *
     * @param levels is the levels list to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // creates level and run it until its over.
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.gameScore);
            level.initialize(this.gui);
            while (!level.isOver()) {
                level.run();
            }
            /*
              if no balls remain in the game - go to lose screen until user press Space.
              When user pressed Space to exit Losing screen - Close gui and break out of the loop
              which leads to ending of the game.
             */
            if (level.getRemainingBallsCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.animationRunner.getGui().getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new LosingEndScreen(this.gameScore)));
                gui.close();
                break;
            }
            // If got here - user won the round, therefore adds him 100 extra points.
            level.levelUp();
        }
        /*
         If we got here - user won the game!
         Therefore go to Winning screen until user presses Space.
         When user pressed Space to exit Winning screen - Close gui and games execution's finished.
         */
        this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new WinningEndScreen(this.gameScore)));
        gui.close();
    }
}
