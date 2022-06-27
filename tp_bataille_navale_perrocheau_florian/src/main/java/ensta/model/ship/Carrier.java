package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {

    /**
     * Default constructor
     */
    public Carrier()
    {
        super('C', "Carrier", 5, Orientation.EAST);
    }

    /** 
     * Valued constructor
     * 
     * @param orientation orientation of the ship
     */
    public Carrier (Orientation orientation)
    {
        super('C', "Carrier", 5, orientation);
    }
}