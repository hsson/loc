package edu.chl.loc.characters;

import edu.chl.loc.utilities.Position2D;

/**
 * General representation of player-objects
 *
 * Created by Maxim on 15-04-05.
 * Version 1.0.0
 * @author Maxim
 */

public abstract class AbstractCharacter {

    /*
        Move character 1 step in a given direction

    */
    public void move(Direction direction){
        Position2D tempPos = getPosition().add(direction.getDelta());
        setPosition(tempPos);

    }

    /*
        Move character 1 step in the position he is currently facing
    */
    public void move(){
        Direction tempDir = getDirection();
        Position2D tempPos = getPosition().add(tempDir.getDelta());
        setPosition(tempPos);

    }

    /*
        Move character in a given change(delta) in X and Y-coordinates
     */
    public void move(double deltaX, double deltaY){
        Position2D tempPos = getPosition().add(deltaX, deltaY);
        setPosition(tempPos);
    }



    /*
        Get character's current position
        @return current position in Position2D
    */
    public abstract Position2D getPosition();


    /*
        Set a new position to the player / updating character's position
    */
    public abstract void setPosition(Position2D position);


	/*
		Set a new position in form of x- and y-coordinates to the character
		More natural to use setPlayerPosition(Position2D pos)
	*/
    public abstract void setPosition(double x, double y);

    /*
        Set the current direction to a given one
     */
    public abstract void setDirection(Direction direction);

    /*
        @return current direction
     */
    public abstract Direction getDirection();

    /*
      @returns X coordinate of character's current position
    */
    public double getX(){
        return getPosition().getX();
    }

    /*
        @returns Y coordinate of character's current position
     */
    public double getY(){
        return getPosition().getY();
    }


}
