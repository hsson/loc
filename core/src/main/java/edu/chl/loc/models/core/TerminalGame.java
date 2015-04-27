package edu.chl.loc.models.core;

import edu.chl.loc.models.characters.Player;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.items.ItemBeverage;
import edu.chl.loc.models.map.*;
import edu.chl.loc.models.utilities.Position2D;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-12
 */
public class TerminalGame {

    private final GameState gameState;

    private final int boardSize = 10;

    private Scanner scanner = new Scanner(System.in);

    public TerminalGame() {
        gameState = new GameState();

        setupGame();
        gameLoop();
    }

    public void setupGame() {
        int beerAmount = 2;
        Random ranGen = new Random();
        GameMap map = gameState.getGameMap();
        ILayer groundLayer = new Layer("ground");
        map.addLayer(new Layer("ground"));
        //generate beers first
        for(int i = 0; i<beerAmount; i++){
            map.addTile(groundLayer, new ItemTile(new ItemBeverage("Prippsblå"), new Position2D(ranGen.nextInt(10),ranGen.nextInt(10))));
        }
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                map.addTile(groundLayer, new Tile(new Position2D(x, y), Math.random() < 0.05));
            }
        }
        Position2D playerPos = gameState.getPlayer().getPosition();
        map.getTile(groundLayer, playerPos).setIsCollidable(false);
    }

    public void gameLoop() {
        while (true) {
            drawGame();
            System.out.println("Move in any direction (w,a,s,d).");
            System.out.print("> ");
            Player p = gameState.getPlayer();
            switch (scanner.nextLine().toCharArray()[0]) {
                case 's':
                    // down is NORTH because of reversed y-axis
                    p.setDirection(Direction.NORTH);
                    break;
                case 'w':
                    // up is SOUTH because of reversed y-axis
                    p.setDirection(Direction.SOUTH);
                    break;
                case 'a':
                    p.setDirection(Direction.WEST);
                    break;
                case 'd':
                    p.setDirection(Direction.EAST);
                    break;
            }
            //System.out.print("\033\143"); //will simulate a terminal buffer, only tested on OSX so far.
                                            //Comment out the print if it doesn't work
                                            //MUST RUN INSIDE TERMINAL; NOT IDE CONSOLE
            if (!gameState.getGameMap().getTile(new Layer("ground"), p.getNextPosition()).isCollidable()) {
                p.move();
            }
            ITile tempTile = gameState.getGameMap().getTile(new Layer("ground"),p.getPosition());
            if(tempTile.hasItem()){ //
                ItemTile itemTile = (ItemTile)tempTile; //only itemtile can have items
                                                        //so this will work w/o checking classes
                try {
                    itemTile.takeItem().use(gameState);
                } catch (EmptyTileException e) {
                    e.printStackTrace();
                }

            }


        }
    }

    public void drawGame() {
       GameMap map = gameState.getGameMap();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                for (ILayer l : map.getLayers()) {
                 ITile t = map.getTile(l, new Position2D(x, y));
                    if (gameState.getPlayer().getPosition().equals(new Position2D(x, y))) {
                        System.out.print("p");
                    } else if (t.isCollidable()) {
                        System.out.print("#");
                    } else if(t.hasItem()){
                        System.out.print("b");
                    } else{
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new TerminalGame();
    }
}
