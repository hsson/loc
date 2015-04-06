package edu.chl.loc.characters;

import edu.chl.loc.utilities.Position2D;

/**
 * Class representing the player
 *
 * Created by Maxim on 15-04-05.
 * @author Maxim
 * Version 1.0.0
 *

 */
public class Player extends AbstractCharacter {
    private Position2D currentPosition;
    private Direction currentDirection;
    /*
        Make sure to add an instance of Inventory when the Inventory class is finished
     */

    public Player(Position2D pos){
        currentPosition = pos;
        currentDirection = Direction.NORTH; //default direction is North.
    }

    public Player(Position2D pos, Direction direction){
        currentDirection = direction;
        currentPosition = pos;
    }


    @Override
    public Position2D getPosition() {
        return currentPosition;
    }

    @Override
    public void setPosition(Position2D position) {
        currentPosition = position;
    }

    @Override
    public void setPosition(double x, double y) {
        currentPosition = new Position2D(x,y);
    }
    @Override
    public void setDirection(Direction direction){
        currentDirection = direction;

    }
    @Override
    public Direction getDirection(){
        return currentDirection;
    }
}

