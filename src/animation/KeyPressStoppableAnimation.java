// ID: 208995035
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A class representing animations that what for a key press.
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation animation;
    private KeyboardSensor keyboardSensor;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor is the keyboard sensor.
     * @param key is the given key to check if pressed.
     * @param animation is the given animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                           Animation animation) {
        this.animation = animation;
        this.keyboardSensor = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        this.isAlreadyPressed = false;
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
