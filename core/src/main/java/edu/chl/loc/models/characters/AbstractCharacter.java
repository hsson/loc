package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.utilities.Position2D;

/**
 * General representation of player-objects
 *
 * Created by Maxim on 15-04-05.
 * Version 2.0.1
 * @author Maxim
 * Revised by Alexander Karlsson, Alexander Håkansson
 */

public abstract class AbstractCharacter {
    private Direction currentDirection;
    private Position2D currentPosition;
    private String characterName;
    private final static String DEFAULT_NAME = "Emil";
    private Gender gender = Gender.MALE;
    private boolean isMoving = false;

    /**
     * Creates an abstractCharacter with a given position, direction, name and inventory
     * @param pos The position to use
     * @param direction The direction to use
     * @param name The name to use
     */
    public AbstractCharacter(Position2D pos, Direction direction, String name, Gender gender){
        this.currentPosition = pos;
        this.currentDirection = direction;
        this.characterName = name;
        this.gender = gender;

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

    public boolean isMoving() {
        return this.isMoving;
    }

    public void setIsMoving(boolean moving) {
        this.isMoving = moving;
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
     * Get the next position in the current direction of the character
     * without actually moving it.
     *
     * @return The next position in the character direction
     */
    public Position2D getNextPosition() {
        return new Position2D(currentPosition.add(currentDirection.getDelta()));
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

    public void setName(String name){
        this.characterName = name;
    }

    public String getName(){
        return characterName;
    }


    /**
     *
     * @param o the object you want to compare with
     * @return true if characters have same position, false otherwise
     */
    public boolean equals(Object o){
        if(this==o){
            return true;
        }else if(o==null || (!this.getClass().equals(o.getClass()))){
            return false;
        }else{
            AbstractCharacter other = (AbstractCharacter)o;
            return this.getPosition().equals(other.getPosition());
        }
    }

    @Override
    public int hashCode(){
        return 491*499*this.getPosition().hashCode();
    }
    /**
     *
     * @return Gender of the character
     */
    public Gender getGender(){
        return this.gender;
    }

    /**
     *
     * @param gender the gender you want to set for this character
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }
}
