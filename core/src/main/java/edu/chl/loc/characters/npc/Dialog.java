package edu.chl.loc.characters.npc;

/**
 * Class for handling an NPCs dialog
 * @author Alexander Karlsson
 * @version 1.0
 */
public class Dialog {
    private String[] dialogStrings;
    private boolean yesOption;

    /**
     * Creates a dialog
     * @param dialogStrings The strings the dialog contains, the string that is last in the
     *                      dialog should be placed last in the array
     * @param yesOption Decides if a dialog is a yes/no question or not. Should be true if the
     *                  dialog is a yes/no question or false if the dialog only has an "Ok" or
     *                  equivalent option
     */
    public Dialog(String[] dialogStrings, boolean yesOption){
        this.dialogStrings = dialogStrings;
        this.yesOption = yesOption;
    }

    /**
     * Specifies if this dialog is a yes/no type question or a question with only one option
     * @return True if the dialog has two yes/no type options, false if the dialog only has
     * one "Ok" type answer
     */
    public boolean hasYesOption(){
        return yesOption;
    }

    /**
     * Gets the strings in the dialog in an array.
     * @return The array with strings. The last string in the dialog is last in the array
     */
    public String[] getDialogStrings(){
        return dialogStrings;
    }
}
