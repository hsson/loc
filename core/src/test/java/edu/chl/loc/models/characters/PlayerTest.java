package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.utilities.Position2D;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Revised by Alexander Håkansson
 *
 * @author  Maxim
 * @version 1.0.0
 * @since 2015-04-11
 */
public class PlayerTest {

    Player player;
    Player otherPlayer;
    Player thirdPlayer;
    Player fourthPlayer;


    @Before
    public void testSetup() {
        player = new Player(new Position2D(4,4), Direction.NORTH, null, null);
        otherPlayer = new Player(new Position2D(), Direction.NORTH, null, null);
    }

    @Test
    public void testPlayerMovement() {
        player.move();
        Assert.assertEquals("The players new Y position should be 1 higher", new Position2D(4, 5), player.getPosition());

    }
    @Test
    public void testPlayerName(){
        player.setName("Emil");
        Assert.assertEquals("The player's name should be Emil", "Emil", player.getName());
        player.setName("AnyOtherName");
        Assert.assertEquals("The player's name should be Emil", "AnyOtherName", player.getName());
    }

    @Test
    public void testPlayerDirection(){
        player.setDirection(Direction.EAST);
        Assert.assertEquals("The player's name should be Emil", Direction.EAST, player.getDirection());
    }
    @Test
    public void testPlayerMovingOneDirection(){
        otherPlayer.setDirection(Direction.SOUTH);
        for(int steps = 0; steps < 10; steps ++){
            otherPlayer.move();
        }
        Assert.assertEquals("The player's position in Y axis should be - 10", new Position2D(0,-10), otherPlayer.getPosition());
    }
    @Test
    public void testPlayerEquality(){
        //Players are considered the same, if their position is the same.
        thirdPlayer = new Player(new Position2D(1337, 9000), Direction.NORTH, null, null);
        fourthPlayer= new Player(new Position2D(1337, 9000), Direction.NORTH, null, null);
        Assert.assertEquals("Test equality of two players by their position", fourthPlayer, thirdPlayer);

    }


    @After
    public void testUnload() {
        player = otherPlayer = thirdPlayer = fourthPlayer = null;
    }

}
