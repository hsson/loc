package edu.chl.loc.models.characters.npc;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.characters.utilities.Gender;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.items.AbstractItem;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.utilities.Position2D;

/**
 * Created by maxim on 15-04-24.
 * @author Maxim Goretskyy
 * Revised by Alexander Karlsson, Alexander Håkansson
 */
public class ItemNPC extends AbstractNPC{
    private Inventory inventory;


    protected ItemNPC(Position2D pos, Direction dir, int id, String name,  Gender gender, Inventory inventory, Dialog dialog) {
        super(pos, dir, id, name, gender, dialog);
        this.inventory = inventory.copy();
    }

    @Override
    public void doAction() {

        // Adds all of this NPC's items to the player
        for (AbstractItem item : inventory.getItems()) {
            GameModel.getPlayer().getInventory().addItem(item);
            getInventory().removeItem(item); //remove item from NPC
        }
    }

    /**
     *
     * @return Inventory that this NPC holds
     */
    public Inventory getInventory(){
        return this.inventory;
    }
}
