// ID: 208995035

import animation.AnimationRunner;
import biuoop.GUI;
import levels.GameFlow;
import levels.FinalFour;
import levels.Green3;
import levels.DirectHit;
import levels.WideEasy;
import levels.LevelInformation;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs Ass6Game.
 */
public class Ass6Game {
    /**
     * Main that runs the game.
     *
     * @param args is the arguments from the user.
     */
    public static void main(String[] args) {
        GUI g = new GUI("Dan's Arknoid Game", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(60, g);
        GameFlow gameFlow = new GameFlow(animationRunner, g);
        List<LevelInformation> l = new ArrayList<>();
        boolean isThereAValidLevel = false;
        /*
        If no argument has passed - run 4 levels.
        If arguments has passed, if they are valid, add the matching level to
        the list of levels to run.
        If no argument was valid, run game like no argument has passed.
         */
        if (args.length == 0) {
            noArgumentsInit(l);
        } else {
            for (String argument : args) {
                switch (argument) {
                    case "1":
                        l.add(new DirectHit());
                        isThereAValidLevel = true;
                        break;
                    case "2":
                        l.add(new WideEasy());
                        isThereAValidLevel = true;
                        break;
                    case "3":
                        l.add(new Green3());
                        isThereAValidLevel = true;
                        break;
                    case "4":
                        l.add(new FinalFour());
                        isThereAValidLevel = true;
                        break;
                    default:
                        break;
                }
            }
            if (!isThereAValidLevel) {
                noArgumentsInit(l);
            }
        }
        gameFlow.runLevels(l);
    }

    /**
     * When no arguments has passed, or all arguments are not valid,
     * initialize game with original 4 levels by increasing order.
     *
     * @param l is the empty list of levels to add levels 1-4 to.
     */
    public static void noArgumentsInit(List<LevelInformation> l) {
        l.add(new DirectHit());
        l.add(new WideEasy());
        l.add(new Green3());
        l.add(new FinalFour());
    }
}
