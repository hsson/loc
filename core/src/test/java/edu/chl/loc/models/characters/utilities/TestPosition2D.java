package edu.chl.loc.models.characters.utilities;

import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maxim on 15-05-10.
 */
public class TestPosition2D {
    @Test
    public void testingGetCoordinates(){
        Position2D pos = new Position2D(9, 21);
        Assert.assertTrue("X coordinate should be 9 and Y should be 21",
                pos.getX() == 9 && pos.getY()==21);
    }

    @Test
    public void testingCopying(){
        Position2D position = new Position2D(1337, 9000);
        Position2D clonePos = position.copy();
        Assert.assertTrue("The clone should have same values as original",
                position.getX() == clonePos.getX() && clonePos.getY() == position.getY());
        Assert.assertTrue("The clone should be the same class as original",
                position.getClass() == clonePos.getClass());

        Assert.assertFalse("Clone is not original", clonePos == position);
    }

    @Test
    public void testingEquals(){
        Position2D firstPos = new Position2D(1337, 9000);
        Position2D secondPos = new Position2D(1337, 9000);
        Assert.assertTrue("Positions are considered the same if they have same x and y values", firstPos.equals(secondPos));

        Assert.assertFalse("Testing equality with null", firstPos.equals(null));

        Assert.assertTrue("Testing equality with itself", firstPos.equals(firstPos));
    }

}
