package edu.chl.loc.models.characters.npc;

/**
 * Exception used in NPCFactory if a value cant be set
 */
public class CannotSetThisValueException extends RuntimeException {
    /**
     * Standard constructor
     */
    public CannotSetThisValueException(){
        super();
    }

    /**
     * Constructor with a string
     * @param string The string
     */
    public CannotSetThisValueException(String string){
        super(string);
    }
}
