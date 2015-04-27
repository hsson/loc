package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.models.utilities.Position2D;
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

    Player player;
    Player otherPlayer;
    Player thirdPlayer;
    Player fourthPlayer;


    @Before
    public void testSetup() {
        player = new Player(new Position2D(4,4));
        otherPlayer = new Player(new Position2D());
    }

    @Test
    public void testPlayerMovement() {
        player.move();
        Assert.assertEquals("The players new Y position should be 1 higher", true, player.getY() == 5);

    }
    @Test
    public void testPlayerName(){
        Assert.assertEquals("The player's name should be Emil", true, player.getName().equals("Emil"));
        player.setName("AnyOtherName");
        Assert.assertEquals("The player's name should be Emil",true,  player.getName().equals("AnyOtherName"));
    }

    @Test
    public void testPlayerDirection(){
        player.setDirection(Direction.EAST);
        Assert.assertEquals("The player's name should be Emil", true, player.getDirection().equals(Direction.EAST));
    }
    @Test
    public void testPlayerMovingOneDirection(){
        otherPlayer.setDirection(Direction.SOUTH);
        for(int steps = 0; steps < 10; steps ++){
            otherPlayer.move();
        }
        Assert.assertEquals("The player's position in Y axis should be - 10", true, otherPlayer.getPosition().equals(new Position2D(0,-10)));
    }
    @Test
    public void testPlayerEquality(){
        //Players are considered the same, if their position is the same.
        thirdPlayer = new Player(new Position2D(1337, 9000));
        fourthPlayer= new Player(new Position2D(1337, 9000));
        Assert.assertEquals("Test equality of two players by their position", true, thirdPlayer.equals(fourthPlayer));

    }


    @After
    public void testUnload() {
        player = otherPlayer = thirdPlayer = fourthPlayer = null;
    }

}
