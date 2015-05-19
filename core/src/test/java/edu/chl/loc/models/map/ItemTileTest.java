package edu.chl.loc.models.map;

import edu.chl.loc.models.items.ItemScore;
import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-30
 * Revised by Maxim Goretskyy
 */
public class ItemTileTest {

    @Test (expected = EmptyTileException.class)
    public void testTakeItemFromEmptyTile() throws EmptyTileException {
        ItemTile itemTile = new ItemTile(new ItemScore("Test", 3), new Position2D());
        itemTile.unsetItem();
        itemTile.takeItem();
    }

    @Test
    public void testHasItem(){
        ItemTile tile = new ItemTile(new ItemScore("TestName", 3), new Position2D());
        Assert.assertTrue("The tile should have an item", tile.hasItem());
        Assert.assertTrue("Item's name is testName", tile.getItem().getItemName().equals("TestName"));
        tile.unsetItem();
        Assert.assertFalse("The tile should not have an item", tile.hasItem());

    }
}
