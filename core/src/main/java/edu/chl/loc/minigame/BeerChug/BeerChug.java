package edu.chl.loc.minigame.BeerChug;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.minigame.BeerChug.beerchugcontroller.BottleBeerChugController;
import edu.chl.loc.minigame.BeerChug.beerchugmodel.BottleBeerChug;
import edu.chl.loc.minigame.BeerChug.beerchugview.BottleBeerChugView;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameListener;
import edu.chl.loc.minigame.MinigameHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for BeerChug minigame
 */
public class BeerChug implements IMinigame, PropertyChangeListener {
    private BottleBeerChug model;
    List<IMinigameListener> listenerList;

    public BeerChug(){
        model = new BottleBeerChug();
        listenerList = new ArrayList<IMinigameListener>();
        model.addPropertyChangeListener(this);
        this.setListener(MinigameHandler.getInstance());
    }

    @Override
    public Screen getView() {
        return new BottleBeerChugView(model);
    }

    @Override
    public InputProcessor getController() {
        return new BottleBeerChugController(model);
    }

    @Override
    public void setListener(IMinigameListener listener) {
        listenerList.add(listener);
    }

    @Override
    public char getGrade() {
        return model.getGrade();
    }

    @Override
    public void reset() {

    }

    private void gameFinished(){
        for(IMinigameListener listener : listenerList){
            listener.minigameFinished();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("gameFinished")){
            this.gameFinished();
        }
    }
}
