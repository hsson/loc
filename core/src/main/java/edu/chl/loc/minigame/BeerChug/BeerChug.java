package edu.chl.loc.minigame.BeerChug;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.minigame.BeerChug.beerchugcontroller.BottleBeerChugController;
import edu.chl.loc.minigame.BeerChug.beerchugmodel.BottleBeerChug;
import edu.chl.loc.minigame.BeerChug.beerchugview.BottleBeerChugView;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameListener;

/**
 * Main class for BeerChug minigame
 */
public class BeerChug implements IMinigame {
    private BottleBeerChug model;
    private BottleBeerChugView view;
    private InputProcessor controller;

    public BeerChug(){
        model = new BottleBeerChug();
        view = new BottleBeerChugView(model);
        controller = new BottleBeerChugController(model);
    }

    @Override
    public Screen getView() {
        return view;
    }

    @Override
    public InputProcessor getController() {
        return controller;
    }

    @Override
    public void setListener(IMinigameListener listener) {

    }
}
