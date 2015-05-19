package edu.chl.loc.models.characters.utilities;

import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Maxim Goretskyy
 * Created by maxim on 15-05-10.
 */
public class DirectionTest {

    @Test
    public void testOppositeDirections(){
        Assert.assertTrue("Opposite of north is south", Direction.NORTH.getOpposite() == Direction.SOUTH);
        Assert.assertTrue("Opposite of south is north", Direction.SOUTH.getOpposite() == Direction.NORTH);
        Assert.assertTrue("Opposite of west is east", Direction.WEST.getOpposite() == Direction.EAST);
        Assert.assertTrue("Opposite of east is west", Direction.EAST.getOpposite() == Direction.WEST);
    }

    @Test
    public void testCorrectDelta(){
        Assert.assertTrue("North's delta should be 0 in x-axis and 1 in y-axis",
                (Direction.NORTH.getDelta().equals(new Position2D(0,1))));

        Assert.assertTrue("South's delta should be 0 in x-axis and -1 in y-axis",
                (Direction.SOUTH.getDelta().equals(new Position2D(0,-1))));

        Assert.assertTrue("West's delta should be -1 in x-axis and 0 in y-axis",
                (Direction.WEST.getDelta().equals(new Position2D(-1,0))));

        Assert.assertTrue("East's delta should be 1 in x-axis and 0 in y-axis",
                (Direction.EAST.getDelta().equals(new Position2D(1,0))));
    }
}
