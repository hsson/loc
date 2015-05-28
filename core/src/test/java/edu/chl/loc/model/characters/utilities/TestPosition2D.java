package edu.chl.loc.model.characters.utilities;

import edu.chl.loc.model.utilities.Position2D;
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

    @Test
    public void testingAddition(){
        Position2D thirdPos = new Position2D(5,9);
        Position2D fourthPos = new Position2D(9, 5);
        Position2D resultPos = thirdPos.add(fourthPos);

        Assert.assertTrue("ResultingPos should have 14 in x and y axis",
                resultPos.getX() == 14 && resultPos.getY()==14);

        Position2D fifthPos = new Position2D(-9, 9);
        Position2D sixthPos = new Position2D(9, 5);
        Position2D secondResultPos = fifthPos.add(sixthPos);
        Assert.assertTrue("SecondResultPos should have 0 in x axis and 14 in y axis",
                secondResultPos.getX()==0 && secondResultPos.getY() == 14);
    }

}
