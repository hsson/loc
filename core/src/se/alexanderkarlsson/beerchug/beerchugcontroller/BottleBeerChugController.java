package se.alexanderkarlsson.beerchug.beerchugcontroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import se.alexanderkarlsson.beerchug.beerchugmodel.BottleBeerChug;
import se.alexanderkarlsson.beerchug.utilities.ShakeDirection;

/**
 * Controller to be used with the BottleBeerChug class
 * @author Alexander Karlsson
 * @version 1.1
 */
public class BottleBeerChugController {
    private BottleBeerChug model;

    public BottleBeerChugController(BottleBeerChug model){
        this.model = model;
    }

    /**
     * Checks if any key has been pushed and updates the model accordingly
     */
    public void update(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(!model.isFirstShakeDone()) {
                model.firstShake();
            }else{
                model.endChug();
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            model.shake(ShakeDirection.LEFT);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            model.shake(ShakeDirection.RIGHT);
        }
    }
}
