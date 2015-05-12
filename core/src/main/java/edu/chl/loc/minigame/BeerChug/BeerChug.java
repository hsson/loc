package edu.chl.loc.minigame.BeerChug;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.minigame.BeerChug.beerchugcontroller.BottleBeerChugController;
import edu.chl.loc.minigame.BeerChug.beerchugmodel.BottleBeerChug;
import edu.chl.loc.minigame.BeerChug.beerchugview.BottleBeerChugView;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for BeerChug minigame
 */
public class BeerChug implements IMinigame {
    private BottleBeerChug model;
    private BottleBeerChugView view;
    private InputProcessor controller;
    List<IMinigameListener> listenerList;

    public BeerChug(){
        model = new BottleBeerChug();
        view = new BottleBeerChugView(model);
        controller = new BottleBeerChugController(model);
        listenerList = new ArrayList<IMinigameListener>();
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
        listenerList.add(listener);
    }

    @Override
    public char getGrade() {
        return model.getGrade();
    }
}
