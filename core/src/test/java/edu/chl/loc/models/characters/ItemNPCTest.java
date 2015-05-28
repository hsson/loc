package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.npc.ItemNPC;
import edu.chl.loc.models.characters.npc.NPCFactory;
import edu.chl.loc.models.core.GameModel;
import edu.chl.loc.models.core.IGameModel;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.items.ItemScore;
import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maxim on 15-05-06.
 */
public class ItemNPCTest {

    ItemNPC itemNPC;
    Inventory inv;
    ItemNPC itemNPCTwo;
    IGameModel model;
    Inventory playerInv;


    @Before
    public void testSetUp(){
        inv = new Inventory();
        playerInv = new Inventory();
        inv.addItem(new ItemScore("PrippsBlå", 3));
        NPCFactory.setInventory(inv, playerInv);
        itemNPC = (ItemNPC)NPCFactory.build(new Position2D(4,4));

        NPCFactory.setInventory(inv, playerInv);
        itemNPCTwo = (ItemNPC)NPCFactory.build(new Position2D(4,4));

        model = new GameModel();
    }


    @Test
    public void testDoingAction(){ //giving all of inventory to the player
        playerInv.clear();
        itemNPC.doAction();
        Assert.assertTrue("itemsNpc should be empty", itemNPC.getInventory().isEmpty());


    }


    @Test
    public void testReceiveItemfromNPC(){
        playerInv.clear();
        Assert.assertTrue("Player's inventory should be empty because it was emptied", playerInv.isEmpty());
        itemNPC.doAction();
        Assert.assertFalse("players inventory should have an item", playerInv.isEmpty());



    }

}
