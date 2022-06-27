package ensta.ai;
import java.util.List;

import ensta.model.Board;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.Coords;
import ensta.model.Hit;

public class PlayerAI extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public PlayerAI(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        this.ai = new BattleShipsAI(ownBoard, opponentBoard);
        this.ships = ships.toArray(new AbstractShip[0]);
    }

    public void putShips()
    {
        this.ai.putShips(this.ships);
    }

    public Hit sendHit(Coords coords)
    {
        return this.ai.sendHit(coords);
    }

    private static AbstractShip[] createDefaultShips()
    {
        return new AbstractShip[] {new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()};
    }
}
