// ID: 208995035
package gameutils;

/**
 * A Class representing counter.
 */
public class Counter {
    private int counter = 0;

    /**
     * Adds number to current count.
     *
     * @param number us the number to add.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number is the number to subtract.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get current count.
     *
     * @return currecnt count.
     */
    public int getValue() {
        return this.counter;
    }
}
