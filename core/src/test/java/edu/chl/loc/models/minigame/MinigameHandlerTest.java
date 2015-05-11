package edu.chl.loc.models.minigame;

import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameHandlerListener;
import edu.chl.loc.minigame.MinigameHandler;
import org.junit.Test;
import org.junit.Assert;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-11
 */
public class MinigameHandlerTest {

    @Test
    public void testInstanceNotNull() {
        MinigameHandler handler = MinigameHandler.getInstance();
        Assert.assertTrue("Handler should return instance", handler != null);
    }

    @Test
    public void testMinigameFinished() {
        MinigameHandler handler = MinigameHandler.getInstance();
        handler.registerListener(new IMinigameHandlerListener() {
            @Override
            public void minigameFinished() {
                Assert.assertTrue(true);
            }

            @Override
            public void startMinigame(IMinigame minigame) {
            }
        });
        handler.minigameFinished();
    }

    @Test
    public void testStartMinigame() {
        MinigameHandler handler = MinigameHandler.getInstance();
        handler.registerListener(new IMinigameHandlerListener() {
            @Override
            public void minigameFinished() {
            }

            @Override
            public void startMinigame(IMinigame minigame) {
                Assert.assertTrue(true);
            }
        });
        handler.startMinigame(null);
    }
}
