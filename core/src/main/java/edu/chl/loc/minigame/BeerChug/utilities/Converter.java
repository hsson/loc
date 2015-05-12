package se.alexanderkarlsson.beerchug.utilities;

/**
 * Class for basic conversions between numbers and strings
 * @author Alexander Karlsson
 * @version 1.0
 */
public class Converter {
    public static String percentToString(float percent){
        float number = percent*100f;
        return (Float.toString(number) + "%");
    }
}
