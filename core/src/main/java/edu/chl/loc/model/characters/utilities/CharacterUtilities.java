package edu.chl.loc.model.characters.utilities;

import edu.chl.loc.model.characters.npc.Dialog;
import edu.chl.loc.model.utilities.FileUtilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by maxim on 15-04-22.
 * @author Maxim Goretskyy
 *
 * Revised by Alexander Håkansson
 * Revised by Alexander Karlsson
 */
public class CharacterUtilities {

    private static String[] maleNames = {"Erik", "Karl", "Anders", "Johan", "Per", "Nils", "Carl", "Jan", "Mikale",
                                         "Lennart", "Hans", "Olof", "Peter", "Gunnar"};

    private static String[] femaleNames ={"Maria", "Anna", "Margareta", "Elisabeth", "Eva", "Kristina", "Birgitta",
                             "Karin", "Marie", "Ingrid", "Christina", "Linnea", "Sofia", "Kerstin", "Helena"};

    private static Gender[] genderArray = {Gender.FEMALE, Gender.MALE, Gender.OTHER};

    private static Random randomGen = new Random();
    private static final String[] DUMMYDIALOG = {"hej"};

    private CharacterUtilities() {
        // Private constructor for disabling instantiation
    }

    /**
     *
     * @return Randomised Gender
     */
    public static Gender generateGender(){
        int randomInt = randomGen.nextInt(genderArray.length);
        return genderArray[randomInt];
    }

    /**
     *
     * @param gender The gender you want to generate a name for
     * @return Name for the given gender, if not male or female, returns a random name
     */
    public static String generateName(Gender gender){
        int randomInt = 0;
        switch(gender){
            case MALE:
                randomInt = randomGen.nextInt(maleNames.length);
                return maleNames[randomInt];
            case FEMALE:
                randomInt = randomGen.nextInt(femaleNames.length);
                return femaleNames[randomInt];

            default:
                double probability = Math.random();
                if(probability < 0.5){
                    return maleNames[randomGen.nextInt(maleNames.length)];
                }
                return femaleNames[randomGen.nextInt(femaleNames.length)];
        }
    }

    /**
     * Generates a standard dialog with no action
     * @return A dialog that can be used by any NPC
     *         that is not important
     */
    public static Dialog generateDialog(){
        List<List<String>> dialogs = null;
        try {
            dialogs = FileUtilities.readFile("Dialogs.loc");
        } catch (FileNotFoundException e) {
            return new Dialog(DUMMYDIALOG, false);
        }
        List<Integer> randomDialogIds = new ArrayList<Integer>();
        for(List<String> stringList: dialogs){
            int id = Integer.parseInt(stringList.get(0));
            if(id>=9000 && id<=9999){
                randomDialogIds.add(id);
            }
        }
        Random random = new Random();
        int index = random.nextInt(randomDialogIds.size());
        try {
            return new Dialog(randomDialogIds.get(index), "Dialogs.loc");
        } catch (FileNotFoundException e) {
            return new Dialog(DUMMYDIALOG, false);
        }
    }

}
