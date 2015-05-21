package edu.chl.loc.minigame.cortege.model;

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

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o == null){
            return false;
        }
        if(!this.getClass().equals(o.getClass())) {
            return false;
        }
        Tool temp = (Tool)o;
        return temp.getType() == this.getType() && temp.getX() == this.getX() &&
                temp.getY() == this.getY();
    }

    @Override
    public int hashCode(){
        return 31*97*getType().hashCode();
    }
}
