package edu.chl.loc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Maxim Goretskyy
 */
public class FileUtilities {

    private FileUtilities(){

    }


    public static List<List<String>> readFile(String pathToFile){

        String[] temp = loadContent(pathToFile);

        List<List<String>> result = new ArrayList<List<String>>();
        for(int i = 0; i<temp.length; i++){
            result.add(removeColons(temp[i]));

        }
        return result;
    }

    public static void printStuff(String[] array){
        for(int i =0; i<array.length; i++){
            System.out.println("Line number " + i + " has " + array[i]);
        }
    }

    private static String[] loadContent(String pathToFile){
        FileHandle file = Gdx.files.internal(pathToFile);
        String test = file.readString();
        String[] result = test.split("\\n");
        return result;
    }

    private static List<String> removeColons(String textString){
        String[] temp = textString.split(":");
        List<String> result = new ArrayList<String>();
        for(int i =0; i<temp.length;i++){
            result.add(temp[i]);
        }
        return result;

    }

    //Testing to print shizzle
    public static void printShizzle(List<List<String>> list){
        for(int i = 0; i<list.size(); i++){
            for(int j = 0; j<list.get(i).size(); j++){
                System.out.println("Word is " +list.get(i).get(j));

            } System.out.println("XXXXXXX");
        }
    }



}
