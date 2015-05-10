package se.alexanderkarlsson.beerchug.beerchugmodel;

import com.badlogic.gdx.utils.TimeUtils;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;

/**
 * Beerchug model for bottle chugs
 * @author Alexander Karlsson
 * @version 1.0.1
 */
public class BottleBeerChug {
    private float centilitersRemaining;
    private ShakeDirection lastShake;
    private long timeElapsed;
    private boolean finished;
    private boolean squirted;
    private boolean chugStarted;
    private boolean firstShakeDone;
    private String disqualifiedReason;

    private static float STARTING_CENTILITRES = 33f;

    /**
     * Basic constructor which starts and creates a basic 33cl beerchug
     */
    public BottleBeerChug(){
        centilitersRemaining = STARTING_CENTILITRES;
        lastShake = null;
        finished = false;
        timeElapsed = 0;
    }

    /**
     * Starts a chug
     */
    public void startChug(){
        chugStarted = true;
    }

    /**
     * Ends a chug
     */
    public void endChug(){
        if(centilitersRemaining != 0){
            squirted = true;
            disqualifiedReason = "Spill";
        }else if(!squirted && !finished){
            finished = true;
        }
    }

    /**
     * Shakes the bottle for the first time, does
     * nothing if first shake is already done
     */
    public void firstShake(){
        if(!firstShakeDone) {
            if (chugStarted()) {
                lastShake = ShakeDirection.LEFT;
                firstShakeDone = true;
            } else {
                squirted = true;
                disqualifiedReason = "Tjuvstart";
            }
        }
    }

    /**
     * Shakes the bottle during a chug and drinks if done properly,
     * otherwise throws exception
     * @param shakeDirection The direction to shake the beer
     */
    public void shake(ShakeDirection shakeDirection){
        if(!chugStarted() || (lastShake == shakeDirection && centilitersRemaining!=0) || !firstShakeDone) {
            squirted = true;
            disqualifiedReason = "Spill";
        }else if(!squirted && centilitersRemaining!=0){
            centilitersRemaining--;
        }
        lastShake = shakeDirection;
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
     * Checks if a chug has started
     * @return False if the countdown is ongoing, false otherwise
     */
    public boolean chugStarted(){
        return chugStarted();
    }

    /**
     * Checks if the first shake has been done
     * @return True if the first shake is done
     */
    public boolean isFirstShakeDone(){
        return firstShakeDone;
    }

    /**
     * Checks which shake was done last
     * @return The last shakes direction
     */
    public ShakeDirection getLastShake(){
        return lastShake;
    }

    /**
     * Returns the percentage left of the bottle
     * @return The percentage of remaining liquid
     */
    public float drinkRemaining(){
        return(float)centilitersRemaining/STARTING_CENTILITRES;
    }

    public String getDisqualifiedReason(){
        if(!squirted){
            return null;
        }else{
            return disqualifiedReason;
        }
    }

    /**
     * Returns how long the current chug has been going on
     * or the final time of the chug in nanoseconds
     * @return The time elapsed if the chug is still ongoing
     * or the final time if the chug is finished
     */
    public long timeElapsed(){
        return timeElapsed;
    }

    /**
     * Updates the time of the ongoing chug
     * @param delta The time to add in nanoseconds
     */
    public void updateTime(float delta){
        if (chugStarted) {
            timeElapsed += delta;
        }
    }

    /**
     * Returns the correct grade based on your chugtime
     * @return U if disqualified or time higher than 7 seconds, 5 if
     * the time is lower than 3 seconds, 4 if the time is lower than
     * 4 seconds and 3 if the time is lower than 7 seconds.
     */
    public char getGrade(){
        if(finished && !squirted) {
            if (timeElapsed() < 3000000000l) {
                return '5';
            } else if (timeElapsed() < 4000000000l) {
                return '4';
            } else if (timeElapsed() < 7000000000l) {
                return '3';
            }
        }
        return 'U';
    }
}
