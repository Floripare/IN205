package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Submarine;
import ensta.model.ship.Destroyer;
import ensta.model.ship.BattleShip;
import ensta.util.Orientation;


public class TestBoard {
    public static void main(String[] args) {
        Board testBoard1 = new Board("Test1");
        Carrier carrier = new Carrier(Orientation.SOUTH);
        Submarine sub = new Submarine(Orientation.WEST);
        Destroyer destr = new Destroyer(Orientation.EAST);
        BattleShip bship = new BattleShip(Orientation.NORTH);
        testBoard1.putShip(carrier, new Coords(1,1));
        testBoard1.putShip(sub, new Coords(4, 4));
        testBoard1.putShip(destr, new Coords(6, 6));
        testBoard1.putShip(bship, new Coords(8, 4));
        //System.out.println(testBoard1.sendHit(new Coords(1,1)));
        //System.out.println(testBoard1.sendHit(new Coords(1,2)));
        //System.out.println(testBoard1.sendHit(new Coords(2,1)));
        //System.out.println(testBoard1.sendHit(new Coords(2,2)));
        //System.out.println(testBoard1.sendHit(new Coords(4,4)));
        testBoard1.print();
    }
}