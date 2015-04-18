package se.alexanderkarlsson.beerchug.beerchugcontroller;

import com.badlogic.gdx.Input;
import se.alexanderkarlsson.beerchug.beerchugmodel.BottleBeerChug;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;

/**
 * Controller to be used with the BottleBeerChug class
 * @author Alexander Karlsson
 * @version 1.0
 */
public class BottleBeerChugController {
    private BottleBeerChug model;

    public BottleBeerChugController(BottleBeerChug model){
        this.model = model;
    }

    public void keyPressed(int keyPressed){
        switch(keyPressed){
            case Input.Keys.SPACE:
                if(model.isFirstShakeDone()) {
                    model.firstShake();
                }else{
                    model.endChug();
                }
                break;
            case Input.Keys.LEFT:
                model.shake(ShakeDirection.LEFT);
                break;
            case Input.Keys.RIGHT:
                model.shake(ShakeDirection.RIGHT);
                break;
        }
    }
}
