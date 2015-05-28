package edu.chl.loc.models.core;

import edu.chl.loc.minigame.IMinigame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.1
 * @since 2015-04-26
 */
public class Stats {

    private Map<String, Object> playerStats = new HashMap<String, Object>();
    private Set<IGameWonListener> listeners = new HashSet<IGameWonListener>();

    private static final double GAME_WON_LIMIT = 300.0;

    private boolean hasWon = false;

    public void addPlayerStat(String key, Object value){
        playerStats.put(key, value);
    }

    public void addGameWonListener(IGameWonListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    private void gameWon() {
        if (!hasWon) {
            hasWon = true;
            for (IGameWonListener l : listeners) {
                l.gameWon();
            }
        }
    }

    public Map<String, Object> getPlayerStats(){
        return new HashMap<String, Object>(playerStats);
    }

    public Set<String> getKeySet(){
        return playerStats.keySet();
    }

    public Object getPlayerStat(String key){
        if (playerStats.containsKey(key)) {
            return playerStats.get(key);
        } else {
            return null;
        }
    }

    public void addHec(double addition){
        Double hec = (Double) getPlayerStat("Högskolepoäng");
        if (hec == null) {
            hec = addition;
        } else {
            hec += addition;
        }

        if (hec >= GAME_WON_LIMIT) {
            gameWon();
        }

        addPlayerStat("Högskolepoäng", hec);
    }

    public double getHec(){
        if(getPlayerStat("Högskolepoäng") == null)
            return 0.0;
        else
            return (Double) getPlayerStat("Högskolepoäng");
    }

    public void addMinigameScore(IMinigame minigame){
        String name = minigame.getName();
        char grade = minigame.getGrade();
        double score = gradeToScore(grade);
        double oldScore = 0;
        char oldGrade = 'U';

        if (playerStats.containsKey(name)) {
            oldGrade = (Character) playerStats.get(name);
            oldScore = gradeToScore(oldGrade);
        }

        double diff = score - oldScore;
        if (diff > 0) {
            addPlayerStat(name, grade);
            addHec(diff);
        } else if (grade == 'U' && oldGrade == 'U') {
            addPlayerStat(name, grade);
        }
    }

    public double gradeToScore(char grade) {
        switch (grade) {
            case 'U':
                return 0.0;
            case '3':
                return 5.0;
            case '4':
                return 10.0;
            case '5':
                return 15.0;
            default:
                return 0.0;
        }
    }
}
