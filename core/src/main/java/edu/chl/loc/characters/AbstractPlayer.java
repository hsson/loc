package edu.chl.loc.characters;

import edu.chl.loc.utilities.Position2D;

/**
 * General representation of player-objects
 *
 * Created by Maxim on 15-04-05.
 * Version 1.0.0
 * @author Maxim
 */

public abstract class AbstractPlayer {

    /*
        Move player 1 step in a given direction

    */
    public void move(Direction direction){
        Position2D tempPos = getPlayerPosition().add(direction.getDelta());
        setPlayerPosition(tempPos);

    }

    /*
        Move player 1 step in the position he is currently facing
    */
    public void move(){
        Direction tempDir = getDirection();
        Position2D tempPos = getPlayerPosition().add(tempDir.getDelta());
        setPlayerPosition(tempPos);

    }

    /*
        Move player in a given change(delta) in X and Y-coordinates
     */
    public void move(double deltaX, double deltaY){
        Position2D tempPos = getPlayerPosition().add(deltaX, deltaY);
        setPlayerPosition(tempPos);
    }



    /*
        Get player's current position
        @return current position in Position2D
    */
    public abstract Position2D getPlayerPosition();


    /*
        Set a new position to the player / updating player's position
    */
    public abstract void setPlayerPosition(Position2D position);


	/*
		Set a new position in form of x- and y-coordinates to the player
		More natural to use setPlayerPosition(Position2D pos)
	*/
    public abstract void setPlayerPosition(double x, double y);

    /*
        Set the current direction to a given one
     */
    public abstract void setDirection(Direction direction);

    /*
        @return current direction
     */
    public abstract Direction getDirection();


}
