package ensta.model.ship;

import ensta.util.Orientation;

public abstract class AbstractShip {
    
    /**
	 * Label of the ship
	 */
    private char label;

    /**
	 * Name of the ship
	 */    
    private String shipName;

    /**
	 * Size of the ship
	 */
    private int shipSize;

    /**
	 * Orientation of the ship
	 */
    private Orientation orientation;

	/**
	 * Number of times the ship has been struck
	 * 
	 */
	private int strikeCount;

	/**
	 * Getter for the label of the ship
	 * 
	 * @return the label of the ship
	 */
    public char getLabel()
    {
        return this.label;
    }

	/**
	 * Getter for the name of the ship
	 * 
	 * @return the name of the ship
	 */
    public String getShipName()
    {
        return this.shipName;
    }

	/**
	 * Getter for the size of the ship
	 * 
	 * @return the size of the ship
	 */
    public int getShipSize()
    {
        return this.shipSize;
    }

	/**
	 * Getter for the number of times a ship has been struck
	 * 
	 * @return the number of times a ship has been struck
	 */
	public int getStrikeCount()
	{
		return this.strikeCount;
	}

	/**
	 * Getter for the orientation of the ship
	 * 
	 * @return the orientation of the ship
	 */
    public Orientation getOrientation()
    {
        return this.orientation;
    }

	/**
	 * Setter for the orientation of the ship
	 * 
     * @param someOrientation defines a new orientation for the ship
	 * @return
	 */
    public void setOrientation(Orientation someOrientation)
    {
        this.orientation = someOrientation;
    }

	/**
	 * Method to increase the strike count of a ship
	 * 
	 * @return
	 */
	public void addStrike()
	{
		this.strikeCount++;
	}

	/**
	 * Method to tell if a ship has been sunk or not 
	 * 
	 * @return true if the ship is sunk, false otherwise
	 */
	public boolean isSunk()
	{
		return this.shipSize == this.strikeCount;
	}

	/**
	 * Valued constructor
	 * 
	 * @param label        label of the ship
	 * @param shipName     name of the ship
     * @param shipSize     size of the ship
     * @param orientation  orientation of the ship
	 */
    public AbstractShip(char label, String shipName, int shipSize, Orientation orientation)
    {
        this.label = label;
        this.shipName = shipName;
        this.shipSize = shipSize;
        this.orientation = orientation;
		this.strikeCount = 0;
    }
}
