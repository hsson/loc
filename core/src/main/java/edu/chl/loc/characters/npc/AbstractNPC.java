package edu.chl.loc.characters.npc;

import edu.chl.loc.characters.AbstractCharacter;
import edu.chl.loc.characters.utilities.Direction;
import edu.chl.loc.characters.utilities.Gender;
import edu.chl.loc.items.Inventory;
import edu.chl.loc.utilities.Position2D;

/**
 * Class for non-playable characters
 * @author Alexander Karlsson && Maxim Goretskyy
 * @version 1.1.2
 */
public abstract class AbstractNPC extends AbstractCharacter {
    //TODO add potential minigame
    //TODO add dialog

    /**
     * Creates an AbstractNPC with a given inventory, direction, name and inventory
     * @param pos The AbstractNPC's position
     * @param dir The direction the AbstractNPC should face
     * @param name The name for the AbstractNPC
     * @param gender The NPCs gender
     */
    protected AbstractNPC(Position2D pos, Direction dir, String name, Gender gender){
        super(pos, dir, name, gender);
    }


    protected abstract void doAction();
}
