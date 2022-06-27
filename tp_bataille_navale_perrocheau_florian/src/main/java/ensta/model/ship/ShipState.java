package ensta.model.ship;

import ensta.util.ColorUtil;

public class ShipState {
    
    /**
     * The ship
     */
    private AbstractShip ship;

    /**
     * The condition of the ship
     */
    private boolean struck;

    /**
     * Method to mark the ship as struck
     * 
     * @return
     */
    public void addStrike() throws Exception
    {
        if (struck) throw new Exception("The ship has been struck already.");
        else 
        {
            this.struck = true;
            this.getShip().addStrike();
        }
    }

    /**
     * Getter for the condition of the ship
     * 
     * @return the condition of the ship
     */
    public boolean isStruck()
    {
        return this.struck;
    }

    /**
     * Method that returns the name of the struck ship
     * 
     * @return the name of the struck ship
     */
    public String toString()
    {
        if(this.struck) return ColorUtil.colorize(this.ship.getLabel(), ColorUtil.Color.RED);
        else return String.valueOf(this.ship.getLabel());
    }

	/**
	 * Method to tell if a ship has been sunk or not 
	 * 
	 * @return true if the ship is sunk, false otherwise
	 */
	public boolean isSunk()
	{
		return this.ship.isSunk();
	}

    /**
     * Getter for the ship
     * 
     * @return the ship
     */
    public AbstractShip getShip()
    {
        return this.ship;
    }

    /**
     * Setter for the ship
     * 
     * @param aShip the ship to set
     */
    public void setShip(AbstractShip aShip)
    {
        this.ship = aShip;
    }

    /**
     * Default constructor
     * 
     */
    public ShipState()
    {
        this.ship = null;
        this.struck = false;
    }

    /**
     * Valued constructor
     * 
     * @param aShip the ship from which the stateShip is built upon
     */
    public ShipState(AbstractShip aShip)
    {
        this.ship = aShip;
        this.struck = false;
    }
}
