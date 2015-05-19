package edu.chl.loc.minigame.cortege;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import edu.chl.loc.minigame.IMinigame;
import edu.chl.loc.minigame.IMinigameListener;
import edu.chl.loc.minigame.MinigameHandler;
import edu.chl.loc.minigame.cortege.controller.CortegeController;
import edu.chl.loc.minigame.cortege.model.CortegeModel;
import edu.chl.loc.minigame.cortege.view.CortegeView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by maxim on 15-05-12.
 * @author Maxim Goretskyy
 */
public class Cortege implements IMinigame, PropertyChangeListener {
    private CortegeModel model;
    private CortegeController controller;
    private CortegeView view;
    List<IMinigameListener> listenerList;

    public Cortege(){
        model = new CortegeModel();
        listenerList = new ArrayList<IMinigameListener>();
        model.addPropertyChangeListener(this);
        this.setListener(MinigameHandler.getInstance());
    }


    @Override
    public Screen getView() {
        return new CortegeView(model);
    }

    @Override
    public InputProcessor getController() {
        return new CortegeController(model);
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
        this.model = new CortegeModel();
        model.addPropertyChangeListener(this);
    }

    private void gameFinished(){
        for(IMinigameListener listener : listenerList){
            listener.minigameFinished(this);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("gameFinished")){
            this.gameFinished();
        }
    }
    public String getName(){
        return "Cortege";
    }
}
