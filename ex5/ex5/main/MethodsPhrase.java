package oop.ex5.main;

import oop.ex5.objects.Method;
import oop.ex5.objects.TypeOneException;
import oop.ex5.objects.Variable;

import java.util.ArrayList;

/**
 * a class that contains a static method to go over inner scopes of the file
 * and extract inner variables and if/while scopes
 *
 * @author unixraz, maayan.lital
 */
public class MethodsPhrase {

    /**
     * Goes over inner scopes of the file
     * and extracts inner variables and if/while scopes
     *
     * @param lines   array from the lines of the given program file.
     * @param methods array list of the given program file's methods objects.
     * @throws TypeOneException in case of invalid method body lines
     */
    public static void phrase(String[] lines, ArrayList<Method> methods)
            throws TypeOneException {
        for (Method method : methods) {
            method.getScope().setGlobalVariables(
                    new ArrayList<Variable>(method.getScope().getGlobalVariables()));
            ScopePhrase.phrase(lines, methods, method.getScope().startLine + 1,
                    method.getScope().endLine - 1, method.getScope());
        }
    }
}
