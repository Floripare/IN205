package ensta.model;

import java.io.Serializable;
import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class Player {
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.board = board;
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;
		int x = 0;
		int y = 0;

		do {
			AbstractShip ship = this.ships[i];
			do{
				this.board.print();
				String msg = String.format("placer %d : %s(%d)", i + 1, ship.getShipName(), ship.getShipSize());
				System.out.println(msg);
				InputHelper.ShipInput res = InputHelper.readShipInput();

				if (res.orientation.charAt(0) == 'n') {
					ship.setOrientation(Orientation.NORTH);
				}

				else if (res.orientation.charAt(0) == 's') {
					ship.setOrientation(Orientation.SOUTH);
				}

				else if (res.orientation.charAt(0) == 'e') {
					ship.setOrientation(Orientation.EAST);
				}

				else if (res.orientation.charAt(0) == 'w') {
					ship.setOrientation(Orientation.WEST);

				} else {
					System.out.println("Error, type n, s, e or w.");
				}

				y = res.y;
				x = res.x;

			} while (!this.board.canPutShip(ship, new Coords(x, y)));

			this.board.putShip(ship, new Coords(x,y));
			++i;
			done = (i == 5);

			this.board.print();
		} while (!done);
	}

	public Hit sendHit(Coords coords) {
		boolean done = false;
		Hit hit = Hit.ERROR;

		do {
			System.out.println("où frapper?");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();

			try{
				hit = opponentBoard.sendHit(new Coords(hitInput.x, hitInput.y));
			} catch (Exception e) {
				System.out.println(e);
			}
			coords.setX(hitInput.x);
			coords.setY(hitInput.y);
			if(hit.getValue() != 0) done = true;
			else System.out.println("Les coordonnées entrées ne sont pas valides.");
		} while (!done);
		board.print();

		return hit;
	}

	public AbstractShip[] getShips() {
		return this.ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
