package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.ai.BattleShipsAI;

public class TestGame {
    
    public static void main(String[] args) {
        Board board = new Board("Test");

        AbstractShip[] ships = createDefaultShips();

        BattleShipsAI ai = new BattleShipsAI(board, board);
        try{
            ai.putShips(ships);
        } catch (Exception e) {
            System.out.println(e);
        }

        int counter = 0;
        int tourCounter = 0;
        Coords coords = new Coords();
        Hit hit;

        while (counter != 4)
        {
            System.out.println();
            System.out.println();
            System.out.println("Tour " + tourCounter++);

            System.out.println();
            hit = ai.sendHit(coords);

            System.out.println();

            switch (hit.getValue())

            {
                case -2:
                    System.out.println("Touché");
                    break;
                case -1:
                    System.out.println("Manqué");
                    break;
                default:
                    if(hit.getValue() > 0)
                    {
                        System.out.println(" -- " + hit + " coulé --");
                        counter++;
                    }
            }
            System.out.println();
            board.print();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static AbstractShip[] createDefaultShips()
    {
        return new AbstractShip[] {new Destroyer(), new Submarine(), new BattleShip(), new Carrier()};
    }
}
