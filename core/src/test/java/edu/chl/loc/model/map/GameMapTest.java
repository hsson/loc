package edu.chl.loc.model.map;

import edu.chl.loc.model.characters.npc.AbstractNPC;
import edu.chl.loc.model.characters.npc.NPCFactory;
import edu.chl.loc.model.utilities.Position2D;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-30
 */
public class GameMapTest {

    @Test
    public void testAddNPC() {
        GameMap map = new GameMap();
        map.addNPC(NPCFactory.build(new Position2D()));
        Assert.assertTrue("NPC should have been added", map.getAllNPCs().size() > 0);
    }

    @Test
    public void testGetNPCFromPosition() {
        GameMap map = new GameMap();
        AbstractNPC npc = NPCFactory.build(new Position2D(2, 2));
        map.addNPC(npc);
        Assert.assertEquals("NPC should be found on position (2, 2)", npc, map.getNPCAtPosition(new Position2D(2, 2)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetNonExistentNPCFromPosition() {
        GameMap map = new GameMap();
        map.getNPCAtPosition(new Position2D());
    }
}
