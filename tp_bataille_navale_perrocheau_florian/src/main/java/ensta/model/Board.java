package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.Orientation;
import ensta.util.ColorUtil;

public class Board implements IBoard{

	/**
	 * Default size of the board
	 */
	private static final int DEFAULT_SIZE = 10;

	/**
	 * Name of the board
	 */
	private String boardName;

	/** 
	 * Size of the grids
	 */
	private int boardSize;

	/**
	 * The player's board
	 */
	private ShipState[][] shipsGrid;
	
	/**
	 * The AI's board
	 */
	private Boolean[][] hitGrid;

	/**
	 * Getter for the name of the board
	 * 
	 * @return the name of the board
	 */
	public String getBoardName() 
	{
		return this.boardName;
	}

	/**
	 * Getter for the size of the grids
	 * 
	 * @return the size of the grids
	 */
	public int getSize() 
	{
		return this.boardSize;
	}

	/**
	 * Getter for the player's board
	 * 
	 * @return the grid representing the player's ships
	 */
	public ShipState[][] getShipsGrid() 
	{
		return this.shipsGrid;
	}

	/**
	 * Getter for the AI's board
	 * 
	 * @return the grid representing the hits made by the player on the AI
	 */
	public Boolean[][] getHitGrid() 
	{
		return this.hitGrid;
	}


	/**
	 * Valued constructor
	 * 
	 * @param name  name of the board
	 * @param size  size of the grids
	 */
	public Board(String name, int size)
	{
		this.boardName = name;
		this.boardSize = size;

		this.shipsGrid = new ShipState[size][size];
        for (int line = 0; line < size; line++) {
            for (int column = 0; column < size; column++) {
                this.shipsGrid[column][line] = new ShipState();
            }
        }

		this.hitGrid = new Boolean[size][size];
        for (int line = 0; line < size; line++) {
            for (int column = 0; column < size; column++) {
                this.hitGrid[column][line] = null;
            }
        }
	}

	/**
	 * Valued constructor
	 * 
	 * @param name  name of the board
	 */
	public Board(String name)
	{
		this.boardName = name;
		this.boardSize = DEFAULT_SIZE;

		this.shipsGrid = new ShipState[DEFAULT_SIZE][DEFAULT_SIZE];
        for (int line = 0; line < DEFAULT_SIZE; line++) {
            for (int column = 0; column < DEFAULT_SIZE; column++) {
                this.shipsGrid[column][line] = new ShipState();
            }
        }

		this.hitGrid = new Boolean[DEFAULT_SIZE][DEFAULT_SIZE];
        for (int line = 0; line < DEFAULT_SIZE; line++) {
            for (int column = 0; column < DEFAULT_SIZE; column++) {
                this.hitGrid[column][line] = null;
            }
        }
	}


	/**
	 * Draw the two boards
	 */
	public void print()
	{
		System.out.print("Navires :");
		for (int space = 2 * getSize() - 7 + 3; space > 0; space--) System.out.print(" ");
		System.out.println("Frappes :");

		System.out.print("   ");
		for (char alphabet = 'A'; alphabet < 'A' + getSize(); alphabet++)
		{
			System.out.print(alphabet + " ");
		}

		System.out.print("  ");

		System.out.print("   ");
		for (char alphabet = 'A'; alphabet < 'A' + getSize() - 1; alphabet++)
		{
			System.out.print(alphabet + " ");
		}
		System.out.println((char) ('A' + getSize() - 1));
		
		for (int line = 1; line <= getSize(); line++)
		{
			if (line < 10)
				System.out.print(line + "  ");
			else
				System.out.print(line + " ");
			for (int column = 1; column <= getSize(); column++)
			{
				if (getShipsGrid()[column-1][line-1].getShip() != null)
					System.out.print(getShipsGrid()[column-1][line-1].toString() + " ");
				else
					System.out.print(". ");
			}
			
			System.out.print("  ");

			if (line < 10)
				System.out.print(line + "  ");
			else
				System.out.print(line + " ");
			for (int column = 1; column <= getSize(); column++)
			{
				if (getHitGrid()[column-1][line-1] == null)
					System.out.print(". ");
				else if (getHitGrid()[column-1][line-1] == false)
					System.out.print("X ");
				else
					System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
			}

			System.out.println("");
		}
		System.out.println("");
	}


    /**
    * Put the given ship at the given position
    * @param ship The ship to place on the board
    * @param coords
    * @return true if the ship is put on the board

    */
    public boolean putShip(AbstractShip ship, Coords coords)
	{

		if(canPutShip(ship, coords))
		{
			Orientation orientation = ship.getOrientation();
			switch(orientation)
			{
				case NORTH : 
					for (int length = 0; length < ship.getShipSize(); length++)
					{
						this.shipsGrid[coords.getX() - 1][coords.getY() - length - 1] = new ShipState(ship);
					}
					break;

					case SOUTH : 
					for (int length = 0; length < ship.getShipSize(); length++)
					{
						this.shipsGrid[coords.getX() - 1][coords.getY() + length - 1] = new ShipState(ship);
					}
					break;

					case EAST : 
					for (int length = 0; length < ship.getShipSize(); length++)
					{
						this.shipsGrid[coords.getX() + length - 1][coords.getY() - 1] = new ShipState(ship);
					}
					break;

					case WEST : 
					for (int length = 0; length < ship.getShipSize(); length++)
					{
						this.shipsGrid[coords.getX() - length - 1][coords.getY() - 1] = new ShipState(ship);
					}
					break;

			}
			return true;
		}
		return false;
	}

    /**
     * Get if a ship is placed at the given position
     * @param coords
     * @return true if a ship is located at the given position
     */
    public boolean hasShip(Coords coords)
	{
		if (coords.getX() < 1 || coords.getY() < 1 || coords.getX() > this.getSize() || coords.getY() > this.getSize())
		{
			return true;
		}
		return !(this.shipsGrid[coords.getX() - 1][coords.getY() - 1].getShip() == null || this.shipsGrid[coords.getX() - 1][coords.getY() - 1].isSunk());
	}

    /**
     * Set the state of the hit at a given position
     * @param hit true if the hit must be set to successful
     * @param coords
     */
    public void setHit(Boolean hit, Coords coords) throws Exception
	{
		if (coords.getX() < 1 || coords.getY() < 1 || coords.getX() > this.getSize() || coords.getY() > this.getSize())
		{
			throw new Exception ("Wrong coordinates.");
		}
		this.hitGrid[coords.getX()-1][coords.getY()-1] = hit;
	}

    /**
     * Get the state of a hit at the given position
     * @param coords
     * @return true if the hit is successful
     */
    public Boolean getHit(Coords coords)
	{
		if (coords.getX() < 1 || coords.getY() < 1 || coords.getX() > this.getSize() || coords.getY() > this.getSize())
		{
			return false;
		}
		return this.hitGrid[coords.getX()-1][coords.getY()-1];
	}

	/**
	 * 
	 * @param res
	 * @return
	 */
	public Hit sendHit(Coords res) throws Exception
	{
		int hitValue = 0;
		ShipState state = this.shipsGrid[res.getX() - 1][res.getY() - 1];

		try {
			state.addStrike();
		} catch (Exception e) {
			hitValue = 0;
		}

		if (state.getShip() == null)
		{
			hitValue = -1;
		} else if (!state.isSunk()) {
			hitValue = -2;
		} else {
			switch (state.getShip().getShipName()) {

				case "Destroyer" :
					hitValue = 2;
					break;

				case "Submarine" :
					hitValue = 3;
					break;

				case "BattleShip" :
					hitValue = 4;
					break;
					
				case "Carrier" :
					hitValue = 5;
					break;
			}
		}
		
		return Hit.fromInt(hitValue);
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getShipSize() >= this.getSize()) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getShipSize() >= this.getSize()) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getShipSize() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getShipSize() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getShipSize(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}
}
