package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {

    /**
     * Default constructor
     */
    public Submarine()
    {
        super('S', "Submarine", 3, Orientation.EAST);
    }

    /** 
     * Valued constructor
     * 
     * @param orientation orientation of the ship
     */
    public Submarine (Orientation orientation)
    {
        super('S', "Submarine", 3, orientation);
    }
}
