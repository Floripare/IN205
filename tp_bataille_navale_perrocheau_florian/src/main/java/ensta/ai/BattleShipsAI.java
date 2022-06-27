package ensta.ai;

import java.io.Serializable;
import java.util.Random;

import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.IBoard;
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class BattleShipsAI implements Serializable {

	/*
	 * ** Attributs
	 */

	/**
	 * My board. My ships have to be put on this one.
	 */
	private final IBoard board;

	/**
	 * My opponent's board. My hits go on this one to strike his ships.
	 */
	private final IBoard opponent;

	/**
	 * Coordss of last known strike. Would be a good idea to target next hits around
	 * this point.
	 */
	private Coords lastStrike;

	/**
	 * If last known strike lead me to think the underlying ship has vertical
	 * placement.
	 */
	private Boolean lastVertical;

	/*
	 * ** Constructeur
	 */

	/**
	 *
	 * @param myBoard       board where ships will be put.
	 * @param opponentBoard Opponent's board, where hits will be sent.
	 */
	public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
		this.board = myBoard;
		this.opponent = opponentBoard;
	}

	/*
	 * ** Méthodes publiques
	 */

	/**
	 * Put the ships on owned board.
	 * 
	 * @param ships the ships to put
	 */
	public void putShips(AbstractShip ships[]) {
		Coords coords = new Coords();
		Orientation[] orientations = Orientation.values();

		Random rand = new Random();

		for (AbstractShip ship : ships) {
			do {
				coords.setX(rand.nextInt(board.getSize()) + 1);
				coords.setY(rand.nextInt(board.getSize()) + 1);
				ship.setOrientation(orientations[rand.nextInt(4)]);
			} while (!board.canPutShip(ship, coords));
			board.putShip(ship, coords);
		}
	}

	/**
	 *
	 * @param coords array must be of size 2. Will hold the coords of the send hit.
	 * @return the status of the hit.
	 */
	public Hit sendHit(Coords coords) {
		Coords res = null;
		Hit hit = null;
		if (coords == null) {
			throw new IllegalArgumentException("must provide an initialized array of size 2");
		}

		// already found strike & orientation?
		if (lastVertical != null) {
			if (lastVertical) {
				try{
					res = pickVCoords();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				try{
					res = pickHCoords();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			if (res == null) {
				// no suitable coords found... forget last strike.
				lastStrike = null;
				lastVertical = null;
			}
		} else if (lastStrike != null) {
			// if already found a strike, without orientation
			// try to guess orientation
			try{
				res = pickVCoords();
			} catch (Exception e) {
				System.out.println(e);
			}
			if (res == null) {
				try{
					res = pickHCoords();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			if (res == null) {
				// no suitable coords found... forget last strike.
				lastStrike = null;
			}
		}

		if (lastStrike == null) {
			try{
				res = pickRandomCoords();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		try {
			hit = opponent.sendHit(res);
		} catch (Exception e) {
			System.out.println(e);
		}

		try{
			board.setHit(hit != Hit.MISS, res);
		} catch (Exception e) {
			System.out.println(e);
		}

		if (hit != Hit.MISS) {
			if (lastStrike != null) {
				lastVertical = guessOrientation(lastStrike, res);
			}
			lastStrike = res;
		}

		coords.setCoords(res);
		return hit;
	}

	/*
	 * *** Méthodes privées
	 */

	private boolean guessOrientation(Coords lastStrike2, Coords res) {
		return lastStrike2.getX() == res.getY();
	}

	private boolean isUndiscovered(Coords coords) {
		return coords.isInBoard(board.getSize()) && board.getHit(coords) == null;
	}

	private Coords pickRandomCoords() throws Exception{
		Coords coords = new Coords();
		do {
			coords = Coords.randomCoords(board.getSize());
		} while (!isUndiscovered(coords));

		return coords;
	}

	/**
	 * pick a coords verically around last known strike
	 * 
	 * @return suitable coords, or null if none is suitable
	 */
	private Coords pickVCoords() throws Exception{
		int x = lastStrike.getX();
		int y = lastStrike.getY();

		for (int iy : new int[] { y - 1, y + 1 }) {
			Coords coords = new Coords(x, iy);
			if (isUndiscovered(coords)) {
				return coords;
			}
		}
		return null;
	}

	/**
	 * pick a coords horizontally around last known strike
	 * 
	 * @return suitable coords, or null if none is suitable
	 */
	private Coords pickHCoords() throws Exception{
		int x = lastStrike.getX();
		int y = lastStrike.getY();

		for (int ix : new int[] { x - 1, x + 1 }) {
			Coords coords = new Coords(ix, y);
			if (isUndiscovered(coords)) {
				return coords;
			}
		}
		return null;
	}
}
