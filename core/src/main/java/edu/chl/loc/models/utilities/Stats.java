package edu.chl.loc.models.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.0
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
