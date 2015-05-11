package edu.chl.loc.minigame;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-11
 */
public interface IMinigameHandlerListener {
    public void minigameFinished();
    public void startMinigame(IMinigame minigame);
}
