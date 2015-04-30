package edu.chl.loc.models.characters;

import edu.chl.loc.models.characters.npc.*;
import edu.chl.loc.models.items.Inventory;
import edu.chl.loc.models.minigames.IMinigame;
import edu.chl.loc.models.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maxim on 15-04-30.
 */
public class NPCFactoryTest {




    @Test
    public void testBuildItemNPC() {
        NPCFactory.setInventory(new Inventory());
        AbstractNPC firstNPC = NPCFactory.build(new Position2D(4,4));
        NPCFactory.reset();
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
        NPCFactory.reset();
        Assert.assertEquals("The created NPC should be a MinigameNPC", secondNPC.getClass(), MinigameNPC.class);

    }
    @Test
    public void testBuildStandardNPC() {
        AbstractNPC thirdNPC = NPCFactory.build(new Position2D(4,4));
        NPCFactory.reset();
        Assert.assertEquals("The created NPC should be a StandardNPC", thirdNPC.getClass(), StandardNPC.class);
    }



}
