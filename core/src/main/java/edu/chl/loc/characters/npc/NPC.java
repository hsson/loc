package edu.chl.loc.characters.npc;

import edu.chl.loc.characters.AbstractCharacter;
import edu.chl.loc.characters.Direction;
import edu.chl.loc.items.Inventory;
import edu.chl.loc.utilities.Position2D;

/**
 * Class for non-playable characters
 * @author Alexander Karlsson
 * @version 1.1
 */
public class NPC extends AbstractCharacter {
    //TODO add potential minigame
    //TODO add dialog

    /**
     * Creates an NPC with a given inventory, direction, name and inventory
     * @param pos The NPC's position
     * @param dir The direction the NPC should face
     * @param name The name for the NPC
     * @param inventory The inventory the NPC should use
     */
    protected NPC(Position2D pos, Direction dir, String name, Inventory inventory){
        super(pos, dir, name, inventory);
    }
}
