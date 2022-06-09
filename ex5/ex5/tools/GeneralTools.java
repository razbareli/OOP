package oop.ex5.tools;

import oop.ex5.objects.Variable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * tools class for ex5
 *
 * @author unixraz, maayan.lital
 */
public class GeneralTools {

    /**
     * Error messages
     */
    public static final String
            ERROR_1 = "ERROR: Could not find the file: ",
            ERROR_2 = "ERROR: IO error occurred.",
            ERROR_3 = "ERROR: Could not close the file ";

    /**
     * returns a sub-array with the given indexes
     *
     * @param arr        the array to slice
     * @param startIndex start index
     * @param endIndex   ens index
     * @return a sub-array
     */
    public static String[] getSliceOfArray(String[] arr,
                                           int startIndex, int endIndex) {
        return Arrays.copyOfRange(arr, startIndex, endIndex);
    }

    /**
     * looks for a variable in an array, by its name
     *
     * @param name              the name of the variable
     * @param arrayForSearching the array to look in
     * @return the variable if it was found, null otherwise
     */
    public static Variable findVariable(String name, ArrayList<Variable> arrayForSearching) {
        Collections.reverse(arrayForSearching);
        for (Variable var : arrayForSearching) {
            if (var.getName().equals(name)) {
                return var;
            }
        }
        return null;
    }

    /**
     * Reads a text file and returns a string array of its lines.
     *
     * @param fileName Text file to read.
     * @return Array with the file's content.
     */
    public static String[] file2array(String fileName) {
        List<String> fileContent = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                fileContent.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println(ERROR_1 + fileName);
            return null;
        } catch (IOException e) {
            System.err.println(ERROR_2);
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
                else
                    return null;
            } catch (IOException e) {
                System.err.println(ERROR_3 + fileName);
            }
        }
        String[] result = new String[fileContent.size()];
        fileContent.toArray(result);
        return result;
    }

}
