// ID: 208995035
package listeners;

/**
 * An Interface representing hit Notifiers.
 * Classes that implement it notify to its listeners that a hit had occurred
 */
public interface HitNotifier {

    /**
     * Add hit Listener as a listener to hit events.
     * @param hl is the hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit Listener from the list of listeners to hit events.
     * @param hl is the hit Listener to remove.
     */
    void removeHitListener(HitListener hl);
}
