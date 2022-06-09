package oop.ex5.main;

import oop.ex5.objects.Method;
import oop.ex5.objects.Scope;
import oop.ex5.objects.TypeOneException;
import oop.ex5.tools.GeneralTools;

import java.util.ArrayList;

/**
 * Phrase given Sjava given file:
 * Prints 0 in case of legal code
 * Prints 1 in case of illegal code
 * Print 2 in case of an error
 *
 * @author unixraz, maayan.lital
 */
public class Sjavac {

    /**
     * Final messages
     */
    public static final int
            SUCCESS_MESSAGE = 0,
            FAIL_MESSAGE = 1,
            ERROR_MESSAGE = 2,
            FILE_ARGV = 0;

    /**
     * Error message
     */
    public static final String ERROR_2 = "ERROR: IO error occurred.";

    /**
     * the main function of the program. Gets the line of the program and phrases them.
     *
     * @param args the given environment argument for the program
     */
    public static void main(String[] args) {
        String[] lines = getLines(args);
        if (lines == null) {
            System.out.println(ERROR_MESSAGE);
            return;
        }
        phrase(lines);
    }

    /**
     * get the given program file's line
     *
     * @param args the given environment argument for the program
     * @return the lines of the given program file to phrase as String array.
     */
    private static String[] getLines(String[] args) {
        if (args.length != 1) {
            System.err.println(ERROR_2);
            return null;
        }
        String testFilePath = args[FILE_ARGV];
        return GeneralTools.file2array(testFilePath);
    }

    /**
     * phrase the line of the given program file.
     *
     * @param lines the lines of the given program file to phrase.
     */
    public static void phrase(String[] lines) {
        try {
            ArrayList<Method> methods = new ArrayList<Method>();
            Scope mainGlobalScope = new Scope();
            GlobalPhrase.phrase(lines, methods, mainGlobalScope);
            MethodsPhrase.phrase(lines, methods);
            System.out.println(SUCCESS_MESSAGE);
        } catch (TypeOneException e) {
            System.out.println(FAIL_MESSAGE);
        }
    }

}
