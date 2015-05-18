package edu.chl.loc.minigame;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

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
            public void minigameFinished(IMinigame minigame) {
                Assert.assertTrue(true);
            }

            @Override
            public void startMinigame(IMinigame minigame) {
            }
        });
        handler.minigameFinished(null);
    }

    @Test
    public void testStartMinigame() {
        MinigameHandler handler = MinigameHandler.getInstance();
        handler.registerListener(new IMinigameHandlerListener() {
            @Override
            public void minigameFinished(IMinigame minigame) {
            }

            @Override
            public void startMinigame(IMinigame minigame) {
                Assert.assertTrue(true);
            }
        });
        handler.startMinigame(null);
    }
}
