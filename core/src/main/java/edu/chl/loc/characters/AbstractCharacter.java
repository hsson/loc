package edu.chl.loc.characters;

import edu.chl.loc.utilities.Position2D;

/**
 * General representation of player-objects
 *
 * Created by Maxim on 15-04-05.
 * Version 2.0.0
 * @author Maxim
 */

public abstract class AbstractCharacter {
    private Direction currentDirection;
    private Position2D currentPosition;


    public AbstractCharacter(Position2D pos) {
        currentPosition = pos;
        currentDirection = Direction.NORTH; //default direction is North.
    }

    public AbstractCharacter(Position2D pos, Direction direction) {
        currentDirection = direction;
        currentPosition = pos;
    }
    /**
     Move character 1 step in a given direction

     */
    public void move(Direction direction){
        setDirection(direction);
        Position2D tempPos = getPosition().add(getDirection().getDelta());
        setPosition(tempPos);

    }

    /**
     Move character 1 step in the position he is currently facing
     */
    public void move(){
        Direction tempDir = getDirection();
        Position2D tempPos = getPosition().add(tempDir.getDelta());
        setPosition(tempPos);

    }

    /**
     Move character in a given change(delta) in X and Y-coordinates
     */
    public void move(double deltaX, double deltaY){
        Position2D tempPos = getPosition().add(deltaX, deltaY);
        setPosition(tempPos);
    }



    /**
     @return current position in Position2D
     */
    public  Position2D getPosition(){
        return currentPosition;
    }


    /**
     * @param position the new position you want to set for the character
     * Updates current position
     */
    public void setPosition(Position2D position){
        currentPosition = position;
    }


    /**
     * @param x X-coordinate you want to set in the new position
     * @param y Y-coordinate you want to set in the new position
    More natural to use setPlayerPosition(Position2D pos)
     */
    public void setPosition(double x, double y){
        currentPosition = new Position2D(x,y);
    }

    /**
     @param direction direction you want to set
     Set the current direction to a given one
     */
    public void setDirection(Direction direction){
        currentDirection = direction;
    }

    /**
     @return current direction
     */
    public Direction getDirection(){
        return currentDirection;
    }

    /**
     @return X coordinate of character's current position
     */
    public double getX(){
        return getPosition().getX();
    }

    /**
     @return Y coordinate of character's current position
     */
    public double getY(){
        return getPosition().getY();
    }


}
