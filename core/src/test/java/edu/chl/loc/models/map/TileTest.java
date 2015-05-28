package edu.chl.loc.models.map;

import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**@author Maxim Goretskyy
 * Created by maxim on 15-05-11.
 */
public class TileTest {

    @Test
    public void testIfStandardTileHasItemAndIsCollidable(){
        Tile tile = new Tile(new Position2D(1337, 9000), true);
        Assert.assertFalse("The tile should not have an item", tile.hasItem());
        Assert.assertTrue("The tile should be collidable", tile.isCollidable());

    }

    @Test
    public void testEquals(){
        //tiles are same only if theirs positions are the same.
        Tile firstTile = new Tile(new Position2D(37, 13), true);
        Tile secondTile = new Tile(new Position2D(37,13), true);

        Tile thirdTile = new Tile(new Position2D(38,13), true);
        Tile fourthTile = new Tile(new Position2D(37,13), false);
        Assert.assertTrue("These tiles should be equal, because of same position",
                firstTile.equals(secondTile));

        Assert.assertFalse("These tiles should not be equal, because of  different position",
                firstTile.equals(thirdTile));

        Assert.assertTrue("These tiles should be equal, because of same position",
                firstTile.equals(fourthTile));

        Assert.assertTrue("These tiles should be equal, because its same tile",
                firstTile.equals(firstTile));

        Assert.assertFalse("Testing with null",
                firstTile.equals(null));

    }
}
