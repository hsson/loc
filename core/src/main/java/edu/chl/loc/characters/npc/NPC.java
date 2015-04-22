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
     * Creates an NPC with an empty inventory but with a specified position and direction to face
     * @param pos The NPC's position
     * @param dir The direction the NPC should face
     */
    protected NPC(Position2D pos, Direction dir, String name, Inventory inventory){
        super(pos, dir, name, inventory);
    }
}
