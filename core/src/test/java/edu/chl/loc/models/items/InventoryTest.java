package edu.chl.loc.models.items;

import org.junit.Assert;
import org.junit.Test;

/**@author Maxim Goretskyy
 * Created by maxim on 15-05-10.
 */
public class InventoryTest {


    @Test
    public void testAddItem(){
        ItemScore tempItem = new ItemScore("PrippsBlå",1);
        Inventory tempInv = new Inventory();
        tempInv.addItem(tempItem);
        Assert.assertFalse("Inventory should not be empty, because added an item", tempInv.isEmpty());
    }

    @Test
    public void testingCopy(){
        Inventory inventory = new Inventory();
        inventory.addItem(new ItemScore("PrippsBlå", 2));

        Inventory secondInv = new Inventory();
        secondInv.addItem(new ItemScore("PrippsBlå", 2));

        Assert.assertTrue("Checking", inventory.equals(secondInv));
    }
    @Test
    public void testingClearInv(){
        Inventory inv = new Inventory();
        inv.addItem(new ItemScore("PrippsBla", 3));
        Assert.assertFalse("Inventory should not be emtpy", inv.isEmpty());

        inv.clear();
        Assert.assertTrue("Inventory should now be emtpy", inv.isEmpty());
    }

    @Test
    public void testItemExist(){
        ItemScore item1 = new ItemScore("PrippsBlå",1);
        Inventory invent = new Inventory();
        invent.addItem(item1);
        Assert.assertTrue("This inv should have item1", invent.hasItem(item1));
    }

    @Test
    public void testItemAmount(){
        ItemScore anotherItem = new ItemScore("PrippsBlå", 3);
        Inventory anotherInv = new Inventory();
        for(int i = 0; i<10; i++) {
            anotherInv.addItem(anotherItem);
        }
        Assert.assertTrue("There should be 10 of PrippsBlå in this inventory", anotherInv.getItemAmount(anotherItem) == 10);
    }

    @Test
    public void testRemoveItem(){
        ItemScore simpleItem = new ItemScore("PrippsBlå",1);
        Inventory simpleInventory = new Inventory();
        simpleInventory.addItem(simpleItem);
        Assert.assertFalse("Inventory should not be emtpy", simpleInventory.isEmpty());

        simpleInventory.removeItem(simpleItem);
        Assert.assertTrue("Inventory should now be emtpy", simpleInventory.isEmpty());
    }


}
