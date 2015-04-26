package edu.chl.loc.models.map;

/**
 * Created by maxim on 15-04-21.
 */
public class EmptyTileException extends Exception{

    public EmptyTileException(){
        super("There is nothing on this tile");
    }

    public EmptyTileException(String message){
        super(message);
    }

}
