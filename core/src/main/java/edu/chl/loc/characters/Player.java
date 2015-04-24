package edu.chl.loc.characters;

import edu.chl.loc.characters.utilities.Direction;
import edu.chl.loc.characters.utilities.Gender;
import edu.chl.loc.items.Inventory;
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
    private Inventory inventory;

    public Player(Position2D pos, Direction direction, String name, Gender gender, Inventory inventory) {
        super(pos, direction, name, gender);
        this.inventory = inventory.copy();
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
     * @return Copy of players inventory
     */
    public Inventory getInventory(){
        return this.inventory.copy();
    }

}


