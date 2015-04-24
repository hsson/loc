package edu.chl.loc.characters.utilities;

import java.util.Random;

/**
 * Created by maxim on 15-04-22.
 * @author Maxim Goretskyy
 *
 * Revised by Alexander Håkansson
 */
public class CharacterUtilities {

    private static String[] maleNames = {"Erik", "Karl", "Anders", "Johan", "Per", "Nils", "Carl", "Jan", "Mikale",
                                         "Lennart", "Hans", "Olof", "Peter", "Gunnar"};

    private static String[] femaleNames ={"Maria", "Anna", "Margareta", "Elisabeth", "Eva", "Kristina", "Birgitta",
                             "Karin", "Marie", "Ingrid", "Christina", "Linnea", "Sofia", "Kerstin", "Helena"};

    private static Gender[] genderArray = {Gender.FEMALE, Gender.MALE};

    private static Random randomGen = new Random();

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


}
