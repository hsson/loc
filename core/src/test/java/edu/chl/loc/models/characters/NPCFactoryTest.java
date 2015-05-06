package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.npc.*;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.minigames.IMinigame;
import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**@author Maxim Goretskyy
 * Created by maxim on 15-04-30.
 *
 */
public class NPCFactoryTest {




    @Test
    public void testBuildItemNPC() {
        NPCFactory.setInventory(new Inventory());
        AbstractNPC firstNPC = NPCFactory.build(new Position2D(4,4));
        Assert.assertEquals("The created NPC should be an ItemNPC", firstNPC.getClass(), ItemNPC.class);

    }

    @Test
    public void testBuildMinigameNPC() {
        NPCFactory.setMinigame(new IMinigame() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        AbstractNPC secondNPC = NPCFactory.build(new Position2D(4,4));
        Assert.assertEquals("The created NPC should be a MinigameNPC", secondNPC.getClass(), MinigameNPC.class);

    }
    @Test
    public void testBuildStandardNPC() {
        AbstractNPC thirdNPC = NPCFactory.build(new Position2D(4,4));
        Assert.assertEquals("The created NPC should be a StandardNPC", thirdNPC.getClass(), StandardNPC.class);
    }

    @Test
    public void testAddInvAndMinigame(){
        NPCFactory.setInventory(new Inventory());
        try {

            NPCFactory.setMinigame(new IMinigame() {
                @Override
                public int hashCode() {
                    return 9;
                }
            });
        }catch(CannotSetThisValueException ex){
            NPCFactory.reset();
            Assert.assertEquals(ex.getMessage(), "Cannot set minigame when inventory is set");

        }

    }

    @Test
    public void testAddMinigameAndInv(){
        NPCFactory.setMinigame(new IMinigame() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        try {
            NPCFactory.setInventory(new Inventory());
        }catch(CannotSetThisValueException ex){
            NPCFactory.reset();
            Assert.assertEquals(ex.getMessage(), "Cannot set inventory when minigame is set");
        }

    }

}
