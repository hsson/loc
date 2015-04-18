package se.alexanderkarlsson.beerchug.beerchugmodel;

import com.badlogic.gdx.utils.TimeUtils;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;

/**
 * Beerchug model for bottle chugs
 * @author Alexander Karlsson
 * @version 1.0
 */
public class BottleBeerChug {
    private int centilitersRemaining;
    private ShakeDirection lastShake;
    private long timeStarted;
    private long timeFinished;
    private boolean finished;
    private boolean squirted;

    public BottleBeerChug(){
        centilitersRemaining = 33;
        lastShake = null;
        timeStarted = TimeUtils.nanoTime()+3000000000l;//Added time for a three second countdown
        finished = false;
    }

    /**
     * Ends a chug
     */
    public void endChug(){
        if(centilitersRemaining != 0){
            squirted = true;
        }else if(!squirted){
            finished = true;
            timeFinished = TimeUtils.nanoTime();
        }
    }

    /**
     * Shakes the bottle during a chug and drinks if done properly,
     * otherwise throws exception
     * @param shakeDirection The direction to shake the beer
     */
    public void shake(ShakeDirection shakeDirection){
        if(timeElapsed()<0 || lastShake == shakeDirection) {
            squirted = true;
        }else if(!squirted){
            lastShake = shakeDirection;
            centilitersRemaining--;
        }

    }

    public boolean isSquirted(){
        return squirted;
    }

    /**
     * Tells you if the chug is finished
     * @return True if the chug is finished, false
     * otherwise
     */
    public boolean isFinished(){
        return finished;
    }

    /**
     * Returns how long the current chug has been going on
     * or the final time of the chug in nanoseconds
     * @return The time elapsed if the chug is still ongoing
     * or the final time if the chug is finished
     */
    public long timeElapsed(){
        if(isFinished()){
            return timeFinished-timeStarted;
        }else{
            return TimeUtils.nanoTime()- timeStarted;
        }
    }
}
