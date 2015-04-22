package edu.chl.loc.characters.utilities;

import java.util.Random;

/**
 * Created by maxim on 15-04-22.
 * @author Maxim Goretskyy
 */
public class CharacterUtilities {

    private static String[] maleNames = {"Erik", "Karl", "Anders", "Johan", "Per", "Nils", "Carl", "Jan", "Mikale",
                                         "Lennart", "Hans", "Olof", "Peter", "Gunnar"};

    private static String[] femaleNames ={"Maria", "Anna", "Margareta", "Elisabeth", "Eva", "Kristina", "Birgitta",
                             "Karin", "Marie", "Ingrid", "Christina", "Linnea", "Sofia", "Kerstin", "Helena"};

    private static Random randomGen = new Random();

    public static String generateName(){
        return "hej";
    }
}
