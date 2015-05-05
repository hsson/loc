package edu.chl.loc;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Maxim Goretskyy
 */
public class FileUtilities {

    private FileUtilities(){

    }

    /**
     *
     * @param pathToFile the path you want to read from, relative to assest (i think and hope)
     * @return List of a List with strings
     */
    public static List<List<String>> readFile(String pathToFile){

        String[] temp = loadContent(pathToFile);

        List<List<String>> result = new ArrayList<List<String>>();
        for(int i = 0; i<temp.length; i++){
            if(!temp[i].equals("")){ // ignores empty strings
                result.add(removeColons(temp[i]));
            }


        }
        return result;
    }

    private static String[] loadContent(String pathToFile){
        String temp1 = load(pathToFile);

        String temp2 = temp1.replaceAll("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)", ""); //ignores all
                                                                                        //java-styled comments
        String temp3 = temp2.replaceAll("^\\s+$", ""); // replaces all white lines with an empty string, like null
        String[] result = temp3.split("\\n"); //Strip every row
        return result;

    }

    /**
     *
     * @param filename Filename you want to read
     * @return String of the whole file.
     */
    private static String load(String filename){

            File file = new File(filename);
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                return new String(bytes,"UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";

    }
    private static List<String> removeColons(String textString){//also ignores whitelines
        String[] temp = textString.split(":");
        List<String> result = new ArrayList<String>();
        for(int i =0; i<temp.length;i++){ //copies to a list
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
        System.out.println("Size of teh list is " + list.size());
    }

}
