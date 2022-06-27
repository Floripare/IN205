package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {

    /**
     * Default constructor
     */
    public BattleShip()
    {
        super('B', "BattleShip", 4, Orientation.EAST);
    }

    /** 
     * Valued constructor
     * 
     * @param orientation orientation of the ship
     */
    public BattleShip (Orientation orientation)
    {
        super('B', "BattleShip", 4, orientation);
    }
}