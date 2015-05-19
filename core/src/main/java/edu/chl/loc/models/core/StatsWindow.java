package edu.chl.loc.models.core;

/**
 * @author Kevin Hoogendijk
 * @version 1.0.0
 * @since 2015-05-18
 */
public class StatsWindow {
    private Stats stats;
    private int scrollIndex;

    public StatsWindow(Stats stats){
        this.stats = stats;
        this.scrollIndex = 0;
    }

    public void scrollDown(){
        if(scrollIndex + 5 < stats.getKeySet().size())
        this.scrollIndex++;
    }

    public void scrollUp() {
        if (scrollIndex > 0) {
            this.scrollIndex--;
        }
    }

    public int getScrollIndex(){
        return this.scrollIndex;
    }

    public Stats getStats(){
        return this.stats;
    }
}
