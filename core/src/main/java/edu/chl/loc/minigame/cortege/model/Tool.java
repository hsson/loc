package edu.chl.loc.minigame.cortege.model;

import edu.chl.loc.minigame.cortege.utilities.ToolType;

import java.awt.*;

/**
 * Created by maxim on 15-05-14.
 * @author Maxim Goretskyy
 */
public class Tool extends Rectangle {


    /**
     * Class to describe a tool, a tool is described by it's type.
     * Same attributes as a rectangle
     */

    private ToolType type;
    public Tool(ToolType type){
        this.type = type;
    }

    public ToolType getType(){
        return this.type;
    }
}
