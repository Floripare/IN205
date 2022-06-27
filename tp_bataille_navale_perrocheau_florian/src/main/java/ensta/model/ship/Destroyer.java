package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {

    /**
     * Default constructor
     */
    public Destroyer()
    {
        super('D', "Destroyer", 2, Orientation.EAST);
    }

    /** 
     * Valued constructor
     * 
     * @param orientation orientation of the ship
     */
    public Destroyer (Orientation orientation)
    {
        super('D', "Destroyer", 2, orientation);
    }
}
