package edu.chl.loc.characters;

import edu.chl.loc.items.Inventory;
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
    private Inventory inventory;
    private String characterName;

    /**
     * Creates an abstractCharacter on a given position, with North as default direction, and with an empty inventory
     * @param pos The position you want AbstractCharacter to have
     */
    public AbstractCharacter(Position2D pos) {
        currentPosition = pos;
        currentDirection = Direction.NORTH; //default direction is North.
        inventory = new Inventory();
    }

    /**
     * Creates an abstractCharacter on a given position, with a given direction and with an empty inventory
     * @param pos The position you want AbstractCharacter to have
     * @param direction The direction you want AbstractCharacter to have
     */
    public AbstractCharacter(Position2D pos, Direction direction) {
        currentDirection = direction;
        currentPosition = pos;
        inventory = new Inventory();
    }

    /**
     * Creates an abstractCharacter on a given position, with North as default direction and with a given inventory
     * @param pos   The position you want AbstractCharacter to have
     * @param name  The name you want AbstractCharacter to have
     * @param inventory The inventory you want AbstractCharacter to have
     */
    public AbstractCharacter(Position2D pos, String name, Inventory inventory) {
        currentPosition = pos;
        currentDirection = Direction.NORTH; //default direction is North.
        this.characterName = name;
        this.inventory = (Inventory)inventory.clone();
    }

    /**
     *  /**
     * Creates an abstractCharacter on a given position and a given name, with North as default direction, and with an empty inventory
     * @param pos   The position you want AbstractCharacter to have
     * @param name  The name you want AbstractCharacter to have
     */
    public AbstractCharacter(Position2D pos, String name) {
        currentPosition = pos;
        currentDirection = Direction.NORTH; //default direction is North.
        this.characterName = name;
        this.inventory = (Inventory)inventory.clone();
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

    public void setName(String name){
        this.characterName = name;
    }

    public String getName(){
        return characterName;
    }

    public void setInventory(Inventory inventory){
        this.inventory = (Inventory) inventory.clone();
    }
    /*
        public Inventory getInventory(){
            return inventory.clone();
        }
    */
}
