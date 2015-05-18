package edu.chl.loc.minigame.caps;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameListener;
import edu.chl.loc.minigame.MinigameHandler;
import edu.chl.loc.minigame.caps.capscontroller.CapsGameController;
import edu.chl.loc.minigame.caps.capsmodel.CapsGameModel;
import edu.chl.loc.minigame.caps.capsview.CapsGameView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for caps minigame
 * @author Alexander Karlsson
 * @version 1.0
 */
public class Caps implements IMinigame, PropertyChangeListener{
    CapsGameModel model;
    List<IMinigameListener> listenerList;

    public Caps(){
        this.model = new CapsGameModel();
        model.addPropertyChangeListener(this);
        this.listenerList = new ArrayList<IMinigameListener>();
        this.setListener(MinigameHandler.getInstance());
    }

    @Override
    public Screen getView() {
        return new CapsGameView(model);
    }

    @Override
    public InputProcessor getController() {
        return new CapsGameController(model);
    }

    @Override
    public void setListener(IMinigameListener listener) {
        listenerList.add(listener);
    }

    @Override
    public double getScore() {
        switch(model.getGrade()){
            case '5':
                return 15;
            case '4':
                return 10;
            case '3':
                return 5;
            default:
                return 0;
        }
    }

    @Override
    public void reset() {
        this.model = new CapsGameModel();
        this.model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("gameFinished")){
            for(IMinigameListener listener : listenerList){
                listener.minigameFinished(this);
            }
        }
    }

    public String getName(){
        return "Caps";
    }
}
