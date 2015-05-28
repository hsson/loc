package edu.chl.loc.model.characters.npc;

import edu.chl.loc.model.characters.utilities.Direction;
import edu.chl.loc.model.characters.utilities.Gender;
import edu.chl.loc.model.items.AbstractItem;
import edu.chl.loc.model.items.Inventory;
import edu.chl.loc.model.utilities.Position2D;

/**
 * Created by maxim on 15-04-24.
 * @author Maxim Goretskyy
 * Revised by Alexander Karlsson, Alexander Håkansson
 */
public class ItemNPC extends AbstractNPC{
    private Inventory inventory;
    private Inventory playerInventory;


    protected ItemNPC(Position2D pos,
                      Direction dir,
                      int id,
                      String name,
                      Gender gender,
                      Inventory inventory,
                      Dialog dialog,
                      Inventory playerInventory) {
        super(pos, dir, id, name, gender, dialog);
        this.inventory = inventory.copy();
        this.playerInventory = playerInventory;
    }

    @Override
    public void doAction() {
        // Adds all of this NPC's items to the player
        for (AbstractItem item : inventory.getItems()) {
            playerInventory.addItem(item);
            inventory.removeItem(item); //remove item from NPC
        }
    }

    /**
     * @return Inventory that this NPC holds
     */
    public Inventory getInventory(){
        return this.inventory;
    }
}
