package edu.chl.loc.model.characters;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameListener;
import edu.chl.loc.model.characters.npc.*;
import edu.chl.loc.model.items.Inventory;
import edu.chl.loc.model.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Maxim Goretskyy
 * @author Alexander Karlsson
 */
public class NPCFactoryTest {

    @Test
    public void testBuildItemNPC() {
        NPCFactory.reset();
        NPCFactory.setInventory(new Inventory(), new Inventory());
        AbstractNPC firstNPC = NPCFactory.build(new Position2D(4,4));
        Assert.assertEquals("The created NPC should be an ItemNPC", firstNPC.getClass(), ItemNPC.class);

    }

    @Test
    public void testBuildMinigameNPC() {
        NPCFactory.reset();
        NPCFactory.setMinigame(new IMinigame() {
            @Override
            public Screen getView() {
                return null;
            }

            @Override
            public InputProcessor getController() {
                return null;
            }

            @Override
            public void setListener(IMinigameListener listener) {

            }

            @Override
            public char getGrade() {
                return  'U';
            }

            @Override
            public void reset() {

            }

            @Override
            public String getName() {
                return null;
            }

        });
        AbstractNPC secondNPC = NPCFactory.build(new Position2D(4,4));
        Assert.assertEquals("The created NPC should be a MinigameNPC", secondNPC.getClass(), MinigameNPC.class);

    }
    @Test
    public void testBuildStandardNPC() {
        NPCFactory.reset();
        AbstractNPC thirdNPC = NPCFactory.build(new Position2D(4,4));
        Assert.assertEquals("The created NPC should be a StandardNPC", thirdNPC.getClass(), StandardNPC.class);
    }

    @Test(expected = CannotSetThisValueException.class)
    public void testAddInvAndMinigame(){
        NPCFactory.reset();
        NPCFactory.setInventory(new Inventory(), new Inventory());
        NPCFactory.setMinigame(new IMinigame() {
            @Override
            public Screen getView() {
                return null;
            }

            @Override
            public InputProcessor getController() {
                return null;
            }

            @Override
            public void setListener(IMinigameListener listener) {

            }

            @Override
            public char getGrade() {
                return 'U';
            }

            @Override
            public void reset() {

            }

            @Override
            public String getName() {
                return null;
            }
        });
    }

    @Test(expected=CannotSetThisValueException.class)
    public void testAddMinigameAndInv(){
        NPCFactory.reset();
        NPCFactory.setMinigame(new IMinigame() {
            @Override
            public Screen getView() {
                return null;
            }

            @Override
            public InputProcessor getController() {
                return null;
            }

            @Override
            public void setListener(IMinigameListener listener) {

            }

            @Override
            public char getGrade() {
                return  'U';
            }

            @Override
            public void reset() {

            }

            @Override
            public String getName() {
                return null;
            }
        });
        NPCFactory.setInventory(new Inventory(), new Inventory());

    }

    @Test
    public void testId(){
        NPCFactory.setId(1);
        AbstractNPC firstNPC = NPCFactory.build(new Position2D());
        NPCFactory.setId(1);
        AbstractNPC secondNPC = NPCFactory.build(new Position2D());
        NPCFactory.setId(1);
        AbstractNPC thirdNPC = NPCFactory.build(new Position2D());
        Assert.assertEquals("Id should be as given", 1, firstNPC.getID());
        Assert.assertTrue("Id should be randomized over 4000", secondNPC.getID() >= 4000);
        Assert.assertFalse("Id should not have same id as previous randomization", secondNPC.getID() == thirdNPC.getID());
    }

}
