package oop.ex5.main;

import oop.ex5.objects.Method;
import oop.ex5.objects.Scope;
import oop.ex5.objects.TypeOneException;
import oop.ex5.objects.Variable;
import oop.ex5.tools.RegexDictionary;
import oop.ex5.validation.ValidateMethodSignature;
import oop.ex5.validation.ValidateVariable;

import java.util.ArrayList;

/**
 * a class that contains a static method to go over the file for the first time
 * and extract global variables and methods
 *
 * @author unixraz, maayan.lital
 */
public class GlobalPhrase {

    /**
     * Goes over the file for the first time
     * and extracts global variables and methods
     *
     * @param lines           array with the lines of the file as strings
     * @param methods         an empty array to add the found methods too
     * @param mainGlobalScope the main global scope of the program
     * @throws TypeOneException if the line does not match any of the legal syntax
     */
    public static void phrase(String[] lines, ArrayList<Method> methods, Scope mainGlobalScope)
            throws TypeOneException {

        ArrayList<Variable> globalVariables = mainGlobalScope.getLocalVariables();
        for (int i = 0; i < lines.length; i++) {
            if (RegexDictionary.methodPattern.matcher(lines[i]).find()) {
                i = ValidateMethodSignature.isValid(lines, i, methods, globalVariables);
                continue;
            }
            if (RegexDictionary.commentPattern.matcher(lines[i]).matches()) {
                continue;
            }
            if (RegexDictionary.emptyPattern.matcher(lines[i]).matches()) {
                continue;
            }

            ValidateVariable.variableValidator(lines[i], mainGlobalScope);
        }
    }
}
