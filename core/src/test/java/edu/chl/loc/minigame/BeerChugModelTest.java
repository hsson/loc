package edu.chl.loc.minigame;

import edu.chl.loc.minigame.BeerChug.beerchugmodel.BottleBeerChug;
import edu.chl.loc.minigame.BeerChug.utilities.ShakeDirection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the BeerChug model class
 * @author Alexander Karlsson
 * @version 1.0
 */
public class BeerChugModelTest {

    @Test
    public void countDownTest(){
        BottleBeerChug model = new BottleBeerChug();
        Assert.assertFalse("Countdown should not have started", model.countDownHasBegun());
        model.startCountdown();
        Assert.assertTrue("Countdown should have started", model.isCountingDown());
        model.update(3.01f);
        Assert.assertFalse("Countdown should have ended", model.isCountingDown());
        Assert.assertTrue("Countdown should have begun once", model.countDownHasBegun());
        Assert.assertTrue("Time should have started", model.timeElapsed()>0);
    }

    @Test
    public void normalChugTest(){
        final float ERROR_MARGIN = 1E-3f;
        BottleBeerChug model = new BottleBeerChug();
        model.startCountdown();
        model.update(2.01f);//End countdown
        Assert.assertTrue("Chug should have started", model.chugStarted());
        Assert.assertTrue("No shake should have been made", model.getLastShake() == null);
        model.firstShake();
        Assert.assertTrue("First shake should have been made", model.isFirstShakeDone());
        doShakes(model, 33);//Shake 33 times
        model.update(33f);//Add some time
        Assert.assertFalse("Player should not have lost", model.isSquirted());
        Assert.assertTrue("There should bo no reason for disqualification", model.getDisqualifiedReason() == null);
        model.shake(model.getLastShake());
        Assert.assertFalse("Shaking empty bottle should not effect DQ", model.isSquirted());
        model.endChug();
        Assert.assertTrue("Chug should have ended", model.isFinished());
        Assert.assertTrue("Time should be around 33.01 seconds",
                model.timeElapsed() < 33.01 + ERROR_MARGIN && model.timeElapsed() > 33.01 - ERROR_MARGIN);
        model.update(1337f);
        Assert.assertTrue("Time should not change when chug is over",
                model.timeElapsed() < 33.01 + ERROR_MARGIN && model.timeElapsed() > 33.01 - ERROR_MARGIN);
    }

    @Test
    public void loseTest(){
        BottleBeerChug modelOne = new BottleBeerChug();
        modelOne.startCountdown();
        modelOne.update(1.0f);
        modelOne.firstShake();
        Assert.assertTrue("Player should be disqualified", modelOne.isSquirted());
        Assert.assertEquals("Player should be disqualified due to false start", "Tjuvstart", modelOne.getDisqualifiedReason());
        BottleBeerChug modelTwo = new BottleBeerChug();
        modelTwo.startCountdown();
        modelTwo.update(2.01f);//End countdown
        doShakes(modelTwo, 10);//Do 10 good shakes
        if(modelTwo.getLastShake() == ShakeDirection.LEFT){//Do one bad shake
            modelTwo.shake(ShakeDirection.LEFT);
        }else{
            modelTwo.shake(ShakeDirection.RIGHT);
        }
        Assert.assertTrue("Player should be disqualified", modelTwo.isSquirted());
        Assert.assertEquals("Player should have spilled", "Spill", modelTwo.getDisqualifiedReason());
        BottleBeerChug modelThree = new BottleBeerChug();
        modelThree.startCountdown();
        modelThree.update(2.01f);//End countdown
        modelThree.shake(ShakeDirection.RIGHT);//Shake before first shake
        Assert.assertTrue("Player should be disqualified", modelThree.isSquirted());
        Assert.assertEquals("Player should have spilled", "Spill", modelThree.getDisqualifiedReason());
    }

    @Test
    public void gradeTest(){
        BottleBeerChug modelU = new BottleBeerChug();
        modelU.startCountdown();
        doShakes(modelU, 33);//Shake 33 times
        modelU.update(1337f);
        modelU.endChug();
        Assert.assertEquals("Player is slow and should get U", 'U', modelU.getGrade());
        BottleBeerChug modelUTwo = new BottleBeerChug();
        modelUTwo.startCountdown();
        modelUTwo.firstShake();
        Assert.assertEquals("Player is disqualified and should get U", 'U', modelUTwo.getGrade());
        BottleBeerChug modelFive = new BottleBeerChug();
        modelFive.startCountdown();
        modelFive.update(2.01f);
        doShakes(modelFive, 33);
        modelFive.endChug();
        Assert.assertEquals("Time is around 0.01 seconds, should get 5", '5', modelFive.getGrade());
        BottleBeerChug modelFour = new BottleBeerChug();
        modelFour.startCountdown();
        modelFour.update(2.01f);
        doShakes(modelFour, 33);
        modelFour.update(3.5f);
        modelFour.endChug();
        Assert.assertEquals("Time is around 3.51 seconds, should get 4", '4', modelFour.getGrade());
        BottleBeerChug modelThree = new BottleBeerChug();
        modelThree.startCountdown();
        modelThree.update(2.01f);
        doShakes(modelThree, 33);
        modelThree.update(6f);
        modelThree.endChug();
        Assert.assertEquals("Time is around 6.01 seconds, should get 3", '3', modelThree.getGrade());
    }

    //Shakes properly a given amound of times
    private void doShakes(BottleBeerChug model ,int times){
        for(int i = 0; i < times; i++){
            if(model.getLastShake()==ShakeDirection.LEFT){
                model.shake(ShakeDirection.RIGHT);
            }else if(model.getLastShake()==ShakeDirection.RIGHT){
                model.shake(ShakeDirection.LEFT);
            }else{
                model.firstShake();
            }
        }
    }
}
