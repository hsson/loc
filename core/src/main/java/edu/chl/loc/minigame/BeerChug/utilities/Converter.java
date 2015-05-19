package edu.chl.loc.minigame.BeerChug.utilities;

import java.text.DecimalFormat;

/**
 * Class for basic conversions between numbers and strings
 * @author Alexander Karlsson
 * @version 1.0
 */
public class Converter {
    public static String percentToString(float percent){
        DecimalFormat format = new DecimalFormat("0.00 '%'");
        float number = percent*100f;
        return format.format(number);
    }
}
