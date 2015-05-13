package edu.chl.loc.minigame;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-11
 */
public class MinigameHandler implements IMinigameListener{

    private static MinigameHandler instance = null;

    private Set<IMinigameHandlerListener> listeners;

    private MinigameHandler() {
        listeners = new HashSet<IMinigameHandlerListener>();
    }

    public static MinigameHandler getInstance() {
        if (instance == null) {
            instance = new MinigameHandler();
        }

        return instance;
    }

    public void registerListener(IMinigameHandlerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IMinigameHandlerListener listener) {
        for (Iterator<IMinigameHandlerListener> iterator = listeners.iterator(); iterator.hasNext();) {
            IMinigameHandlerListener l = iterator.next();
            if (l.equals(listener)) {
                iterator.remove();
            }
        }
    }

    public void minigameFinished() {
        for (IMinigameHandlerListener listener : listeners) {
            listener.minigameFinished();
        }
    }

    public void startMinigame(IMinigame minigame) {
        for (IMinigameHandlerListener listener : listeners) {
            listener.startMinigame(minigame);
        }
    }
}
