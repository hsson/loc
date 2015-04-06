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
        Position2D tempPos = getCharacterPosition().add(direction.getDelta());
        setCharacterPosition(tempPos);

    }

    /*
        Move character 1 step in the position he is currently facing
    */
    public void move(){
        Direction tempDir = getDirection();
        Position2D tempPos = getCharacterPosition().add(tempDir.getDelta());
        setCharacterPosition(tempPos);

    }

    /*
        Move character in a given change(delta) in X and Y-coordinates
     */
    public void move(double deltaX, double deltaY){
        Position2D tempPos = getCharacterPosition().add(deltaX, deltaY);
        setCharacterPosition(tempPos);
    }



    /*
        Get character's current position
        @return current position in Position2D
    */
    public abstract Position2D getCharacterPosition();


    /*
        Set a new position to the player / updating character's position
    */
    public abstract void setCharacterPosition(Position2D position);


	/*
		Set a new position in form of x- and y-coordinates to the character
		More natural to use setPlayerPosition(Position2D pos)
	*/
    public abstract void setCharacterPosition(double x, double y);

    /*
        Set the current direction to a given one
     */
    public abstract void setDirection(Direction direction);

    /*
        @return current direction
     */
    public abstract Direction getDirection();


}
