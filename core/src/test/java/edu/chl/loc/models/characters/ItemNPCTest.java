package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.npc.ItemNPC;
import edu.chl.loc.models.characters.npc.NPCFactory;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.items.ItemScore;
import edu.chl.loc.models.utilities.Position2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maxim on 15-05-06.
 */
public class ItemNPCTest {

    ItemNPC itemNPC;
    Inventory inv;

    @Before
    public void testSetUp(){
        inv.addItem(new ItemScore("PrippsBlå", 3));
        NPCFactory.setInventory(inv);
        itemNPC = (ItemNPC)NPCFactory.build(new Position2D(4,4));
    }

    @Test
    public void testDoingAction(){ //giving all of inventory to the player


    }

    @Test
    public void testReadingDialog(){

    }
}
