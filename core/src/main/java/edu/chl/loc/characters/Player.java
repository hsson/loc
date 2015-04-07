package edu.chl.loc.characters;

import edu.chl.loc.utilities.Position2D;

/**
 * Class representing the player
 *
 * Created by Maxim on 15-04-05.
 * @author Maxim
 * Version 2.0.0
 *

 */
public class Player extends AbstractCharacter {
    private Position2D currentPosition;
    private Direction currentDirection;
    /*
        Make sure to add an instance of Inventory when the Inventory class is finished
     */

    public Player(Position2D pos) {
        super(pos);
    }

    public Player(Position2D pos, Direction direction) {
        super(pos, direction);
    }

}


