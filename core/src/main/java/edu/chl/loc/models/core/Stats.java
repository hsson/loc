package edu.chl.loc.models.core;

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

    private double hec = 0;
    private Map<String, Double> playerStats = new HashMap<String, Double>();
    private Set<IGameWonListener> listeners = new HashSet<IGameWonListener>();

    public void addPlayerStat(String key, Double value){
        if(playerStats.containsKey(key)){
            double prevValue = playerStats.get(key);
            value = prevValue + value;
        }
        playerStats.put(key, value);
    }

    // TODO: Check for game won
    public void addGameWonListener(IGameWonListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    private void gameWon() {
        for (IGameWonListener l : listeners) {
            l.gameWon();
        }
    }

    public void setPlayerStat(String key, Double value){
        playerStats.put(key, value);
    }

    public Map<String, Double> getPlayerStats(){
        return new HashMap<String, Double>(playerStats);
    }

    public Set<String> getKeySet(){
        return playerStats.keySet();
    }

    public double getPlayerStat(String key){
        return playerStats.get(key);
    }

    public void addHec(double addition){
        hec += addition;
    }

    public double getHec(){
        return hec;
    }

    public void addMinigameStat(String key, Double value){
        double temp;
        if(playerStats.containsKey(key)){
            temp = playerStats.get(key);
            if(temp > value){
                value = temp;
            }
        }
        playerStats.put(key, value);
    }
}
