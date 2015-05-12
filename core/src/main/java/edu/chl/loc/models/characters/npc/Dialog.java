package edu.chl.loc.models.characters.npc;

import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.utilities.FileUtilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling an NPCs dialog
 * @author Alexander Karlsson
 * @version 1.1
 */
public class Dialog {
    private String[] dialogStrings;
    private String currentString;
    private int currentStringIndex;
    private boolean yesOption;
    private boolean optionSelected = true;

    /**
     * Creates a dialog
     * @param dialogStrings The strings the dialog contains, the string that is last in the
     *                      dialog should be placed last in the array
     * @param yesOption Decides if a dialog is a yes/no question or not. Should be true if the
     *                  dialog is a yes/no question or false if the dialog only has an "Ok" or
     *                  equivalent option
     */
    public Dialog(String[] dialogStrings, boolean yesOption){
        this.dialogStrings = (String[])dialogStrings.clone();
        this.yesOption = yesOption;
        currentStringIndex = 0;
        currentString = dialogStrings[currentStringIndex];
    }

    /**
     * Creates a dialog from an id, the dialog will be created using a file with a given path.
     * For proper formatting of this file see additional documentation
     * @param id The id corresponding to a dialog id in the file
     * @param path Path to the file
     */

    public Dialog(int id, String path) throws InvalidIdException, FileNotFoundException {
        List<List<String>> dialogList = FileUtilities.readFile(path);
        boolean idFound = false;
        for(int i = 0; i < dialogList.size(); i++){
            List<String> currentList = dialogList.get(i);
            int readId = 0;
            try{
                readId = Integer.parseInt(currentList.get(0));
            }catch(NumberFormatException e){
                throw new InvalidIdException();
            }
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

        currentStringIndex = 0;
        currentString = dialogStrings[currentStringIndex];
    }

    /**
     * Converts a list of strings to a Dialog, the first string should be "yes" if
     * the dialog has a yes and no option. The rest of the strings should be the messages
     * in order of appearance
     * @param dialogStrings
     * @return
     */
    private Dialog listToDialog(List<String> dialogStrings){
        String yesString = dialogStrings.get(0);
        boolean yesOption = yesString.equals("yes");
        List<String> strippedList = new ArrayList<String>(dialogStrings);
        strippedList.remove(0);
        String[] dialogArray = strippedList.toArray(new String[0]);
        return new Dialog(dialogArray, yesOption);
    }

    public void setOptionSelected(boolean option){
        this.optionSelected = option;
    }

    public boolean getOptionSelected(){
        return this.optionSelected;
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
     * Sets the next string to the current
     */
    public void setNextString(){
        currentString = dialogStrings[++currentStringIndex];
    }

    /**
     * get the current active string in the dialog
     * @return the active string
     */
    public String getCurrentString(){
        return currentString;
    }

    /**
     * returns true if the active string is the last one in the dialog
     * @return
     */
    public boolean isLastString(){
        return currentStringIndex == dialogStrings.length - 1;
    }

    public void resetDialog(){
        this.currentStringIndex = 0;
        this.currentString = dialogStrings[0];
    }
    /**
     * Gets the strings in the dialog in an array.
     * @return The array with strings. The last string in the dialog is last in the array
     */
    public String[] getDialogStrings(){
        return dialogStrings;
    }
}
