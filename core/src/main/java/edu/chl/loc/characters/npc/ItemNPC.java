package edu.chl.loc.characters.npc;

import edu.chl.loc.characters.utilities.Direction;
import edu.chl.loc.characters.utilities.Gender;
import edu.chl.loc.items.Inventory;
import edu.chl.loc.utilities.Position2D;

/**
 * Created by maxim on 15-04-24.
 * @author Maxim Goretskyy
 */
public class ItemNPC extends AbstractNPC{
    private Inventory inventory;


    protected ItemNPC(Position2D pos, Direction dir, String name,  Gender gender) {
        super(pos, dir, name, gender);
    }

    @Override
    protected void doAction() {
        //Todo action to give an item
    }
}
