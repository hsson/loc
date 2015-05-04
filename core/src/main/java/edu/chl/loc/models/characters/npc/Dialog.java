package edu.chl.loc.models.characters.npc;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling an NPCs dialog
 * @author Alexander Karlsson
 * @version 1.1
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
     * Creates a dialog from an id, the dialog will be created using a file named "Dialogs.log".
     * For proper formatting of this file see additional documentation
     * @param id The id corresponding to a dialog in "Dialogs.loc"
     */
    public Dialog(int id){
        List<List<String>> dialogList = FileUtilities.readFile("Dialogs.loc");
        boolean idFound = false;
        for(int i = 0; i < dialogList.size(); i++){
            List<String> currentList = dialogList.get(i);
            int readId = Integer.parseInt(currentList.get(0));
            if(readId == id){
                List<String> newList = new ArrayList<String>(currentList);
                newList.remove(0);
                Dialog dialog = listToDialog(newList);
                this.dialogStrings = dialog.dialogStrings.clone();
                this.yesOption = dialog.yesOption;
                idFound = true;
                break;
            }
        }
        if(!idFound){
            throw new InvalidIdException();
        }
    }

    /**
     * Converts a list of strings to a Dialog, the first string should be "yes" if
     * the dialog has a yes and no option. The rest of the strings should be the messages
     * in order of appearance
     * @param dialogStrings
     * @return
     */
    public Dialog listToDialog(List<String> dialogStrings){
        String yesString = dialogStrings.get(0);
        boolean yesOption = yesString.equals("yes");
        List<String> strippedList = new ArrayList<String>(dialogStrings);
        strippedList.remove(0);
        String[] dialogArray = strippedList.toArray(new String[0]);
        return new Dialog(dialogArray, yesOption);
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
