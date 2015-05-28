package edu.chl.loc.model.characters.npc;

import edu.chl.loc.model.characters.AbstractCharacter;
import edu.chl.loc.model.characters.utilities.Direction;
import edu.chl.loc.model.characters.utilities.Gender;
import edu.chl.loc.model.utilities.Position2D;

/**
 * Class for non-playable characters
 * @author Alexander Karlsson
 * @author Maxim Goretskyy
 * @version 1.1.3
 */
public abstract class AbstractNPC extends AbstractCharacter {
    private Dialog dialog;
    private int id;

    /**
     * Creates an AbstractNPC with a given inventory, direction, name and inventory
     * @param pos The AbstractNPC's position
     * @param dir The direction the AbstractNPC should face
     * @param name The name for the AbstractNPC
     * @param gender The NPCs gender
     */
    protected AbstractNPC(Position2D pos, Direction dir, int id, String name, Gender gender, Dialog dialog){
        super(pos, dir, name, gender);
        this.dialog = dialog;
        this.id = id;
    }


    public abstract void doAction();

    public int getID() {
        return id;
    }

    public Dialog getDialog() {
        return dialog;
    }
}
