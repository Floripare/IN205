package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.ai.PlayerAI;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.ai.BattleShipsAI;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player_human;
	private Player player_ai;
	private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		if (!loadSave()) {

			this.sin = new Scanner(System.in);

			// statement of player's name
			System.out.println("Enter your board's name :");
			String name = this.sin.nextLine();

			// initialising the two boards
			Board board_player = new Board(name);
			Board board_ai = new Board("AI opponent");

			// initialising the two players
			this.player_human = new Player (board_player, board_ai, createDefaultShips());
			this.player_ai = new PlayerAI (board_ai, board_player, createDefaultShips());

			//place players' ships
			player_human.putShips();
			player_ai.putShips();

		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	public void run() {
		Coords coords_player = new Coords();
		Coords coords_ai = new Coords();
		Board b1 = this.player_human.getBoard();
		Hit hit_player;
		Hit hit_ai;

		// main loop
		b1.print();
		boolean done;
		do {
			hit_player = this.player_human.sendHit(coords_player);

			boolean strike = (hit_player != Hit.MISS);

			try {
				b1.setHit(strike, coords_player);
			} catch (Exception e) {
				e.printStackTrace();
			}

			done = updateScore();
			b1.print();
			System.out.println(makeHitMessage(false /* outgoing hit */, coords_player, hit_player));

			// save();

			if (!done && !strike) {
				do {
					hit_ai = player_ai.sendHit(coords_ai); 


					strike = (hit_ai != Hit.MISS);
					if (strike) {
						b1.print();
					}
					System.out.println(makeHitMessage(true /* incoming hit */, coords_ai, hit_ai));
					done = updateScore();

					if (!done) {
//						save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player_human.isLose() ? 2 : 1));
		sin.close();
	}

	private void save() {
//		try {
//			// TODO bonus 2 : uncomment
//			// if (!SAVE_FILE.exists()) {
//			// SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			// }
//
//			// TODO bonus 2 : serialize players
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private boolean loadSave() {
//		if (SAVE_FILE.exists()) {
//			try {
//				// TODO bonus 2 : deserialize players
//
//				return true;
//			} catch (IOException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player_human, player_ai }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
		case MISS:
			msg = hit.toString();
			break;
		case STRIKE:
			msg = hit.toString();
			color = ColorUtil.Color.RED;
			break;
		default:
			msg = hit.toString() + " coulé";
			color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX() - 1)),
				(coords.getY()), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
				new Carrier() });
	}
}
