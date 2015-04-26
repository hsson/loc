package edu.chl.loc.models.characters.npc;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.utilities.Position2D;

/**
 * Created by maxim on 15-04-24.
 * @author Maxim Goretskyy
 */
public class ItemNPC extends AbstractNPC{
    private Inventory inventory;


    protected ItemNPC(Position2D pos, Direction dir, String name,  Gender gender, Inventory inventory, Dialog dialog) {
        super(pos, dir, name, gender, dialog);
        this.inventory = inventory;
    }

    @Override
    protected void doAction() {
        //Todo action to give an item
    }
}
