package edu.chl.loc.characters;

import edu.chl.loc.utilities.Position2D;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author  Maxim
 * @version 1.0.0
 * @since 2015-04-11
 */
public class PlayerTest {

    Player play2;



    @Before
    public void testSetup() {
        play2 = new Player(new Position2D(4,4));
    }

    @Test
    public void testPlayerMovement() {
        play2.move();
        Assert.assertEquals("The players new Y position should be 1 higher", true, play2.getY() == 5);

    }
    @After
    public void testUnload() {
        play2 = null;
    }

}
