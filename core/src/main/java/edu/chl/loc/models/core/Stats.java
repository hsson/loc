package edu.chl.loc.models.core;

import java.util.HashMap;
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

    public void addPlayerStat(String key, Double value){
        if(playerStats.containsKey(key)){
            double prevValue = playerStats.get(key);
            value = prevValue + value;
        }
        playerStats.put(key, value);
        System.out.println(playerStats.size());
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
}
