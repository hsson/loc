package edu.chl.loc.models.utilities;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-11
 */
public class GameUtilitiesTest {
    @Test
    public void testSimpleDistance() {
        Position2D pos1 = new Position2D(0,0);
        Position2D pos2 = new Position2D(0,1);
        int distance = 1;
        Assert.assertTrue("The distance is not correct", distance == GameUtilities.calculateDistance(pos1, pos2));
    }

    @Test
    public void testDiagonalDistance() {
        Position2D pos1 = new Position2D(0,0);
        Position2D pos2 = new Position2D(5,5);
        double distance = Math.sqrt(50);
        Assert.assertTrue("The distance is not correct", distance == GameUtilities.calculateDistance(pos1, pos2));
    }
}
