package edu.chl.loc.characters;

import edu.chl.loc.Items.Inventory;
import edu.chl.loc.utilities.Position2D;

/**
 * Class for non-playable characters
 * @author Alexander Karlsson
 * @version 1.0
 */
public class NPC extends AbstractCharacter{
    private Inventory inventory;
    //TODO add potential minigame
    //TODO add dialog

    /**
     * Creates an NPC with an empty inventory on specified position
     * @param pos The position the NPC should have
     */
    public NPC(Position2D pos){
        super(pos);
        inventory = new Inventory();
    }

    /**
     * Creates an NPC with an empty inventory but with a specified position and direction to face
     * @param pos The NPC's position
     * @param dir The direction the NPC should face
     */
    public NPC(Position2D pos, Direction dir){
        super(pos, dir);
        inventory = new Inventory();
    }
}
