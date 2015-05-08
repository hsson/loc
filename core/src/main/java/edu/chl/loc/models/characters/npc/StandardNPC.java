package edu.chl.loc.models.characters.npc;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.utilities.Position2D;

/**
 * Created by maxim on 15-04-24.
 * @author Maxim Goretskyy
 * Revised by Alexander Karlsson
 */
public class StandardNPC extends AbstractNPC {

    protected StandardNPC(Position2D pos, Direction dir, int id, String name, Gender gender, Dialog dialog) {
        super(pos, dir, id, name, gender, dialog);
    }

    @Override
    public void doAction() {

    }
}
