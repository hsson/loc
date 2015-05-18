package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.utilities.Position2D;

/**
 * Class representing the player
 *
 * Revised by Alexander Håkansson
 *
 * Created by Maxim on 15-04-05.
 * @author Maxim
 * Version 2.0.0
 *

 */
public class Player extends AbstractCharacter {
    private Inventory inventory;

    public Player(Position2D pos, Direction direction, String name, Gender gender) {
        super(pos, direction, name, gender);
        this.inventory = new Inventory();

        // TODO: Remove debug code when smooth movement is implemented
        setIsMoving(true);
    }

    /**
     * Sets an inventory to this character
     * @param inventory Inventory you want to set for the character
     */
    public void setInventory(Inventory inventory){
        this.inventory = inventory.copy();
    }

    /**
     * Get players inventory
     * @return Players inventory
     */
    public Inventory getInventory(){
        return this.inventory;

    }

}


