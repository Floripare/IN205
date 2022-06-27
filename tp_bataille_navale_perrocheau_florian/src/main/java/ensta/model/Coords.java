package ensta.model;

import java.util.Random;

public class Coords {
    
    /**
     * X-coordinate
     */
    private int X;

    /**
     * Y-coordinate
     */
    private int Y;

	/**
	 * Getter for the X-coordinate
	 * 
	 * @return the X-coordinate
	 */
    public int getX()
    {
        return this.X;
    }

	/**
	 * Getter for the Y-coordinate
	 * 
	 * @return the Y-coordinate
	 */
    public int getY()
    {
        return this.Y;
    }

	/**
	 * Setter for the X-coordinate
	 * 
	 * @param someX the new X-coordinate
     * @return
	 */
    public void setX(int someX)
    {
        this.X = someX;
    }

	/**
	 * Setter for the Y-coordinate
	 * 
	 * @param someY the new Y-coordinate
     * @return
	 */
    public void setY(int someY)
    {
        this.Y = someY;
    }

    /**
     * Setter for both coordinates
     * @param someCoords
     * @return
     */
    public void setCoords(Coords someCoords)
    {
        this.X = someCoords.getX();
        this.Y = someCoords.getY();
    }

    /**
     * Method to pick two random coordinates
     * 
     * @param boardSize the size of the board
     * @return a set of random coordinates on the board
     */
    public static Coords randomCoords(int boardSize)
    {
        Coords coords = new Coords();
        Random rand = new Random();
        coords.setX(rand.nextInt(boardSize) + 1);
        coords.setY(rand.nextInt(boardSize) + 1);
        return coords;
    }

    /**
     * Method to determine if a set of coordinates belongs to the board
     * 
     * @param boardSize the size of the board
     * @return true if the coordinates are in the board
     */
    public boolean isInBoard(int boardSize)
    {
        int X = this.getX();
        int Y = this.getY();
        return (X > 0 && Y > 0 && X <= boardSize && Y <= boardSize);
    }


    /**
     * Valued constructor
     * 
     * @param X the X-coordinate
     * @param Y the Y-coordinate
     */
    public Coords(int someX, int someY)
    {
        this.X = someX;
        this.Y = someY;
    }

    /**
     * Copy constructor
     * 
     * @param coords another set of coordinates
     */
    public Coords(Coords coords)
    {
        this.X = coords.getX();
        this.Y = coords.getY();
    }

    /**
     * Base constructor
     * 
     * @param X the X-coordinate
     * @param Y the Y-coordinate
     */
    public Coords()
    {
        this.X = 0;
        this.Y = 0;
    }
}
