package oop.ex5.main;

import oop.ex5.objects.*;
import oop.ex5.tools.RegexDictionary;
import oop.ex5.validation.ValidateIfWhile;
import oop.ex5.validation.ValidateMethodCall;
import oop.ex5.validation.ValidateVariable;

import java.util.ArrayList;

/**
 * phrase scope body lines
 *
 * @author unixraz, maayan.lital
 */
public class ScopePhrase {

    /**
     * function that phrase given lines that represent scope lines
     *
     * @param lines     array from the lines of the given program file.
     * @param methods   array list of the given program file's methods objects.
     * @param startLine the start line index of the scope
     * @param endLine   the end line index of the scope
     * @param scope     the Scope object of the scope
     * @throws TypeOneException in case of invalid line
     */
    public static void phrase(String[] lines, ArrayList<Method> methods,
                              int startLine, int endLine, Scope scope)
            throws TypeOneException {
        for (int i = startLine; i < endLine; i++) {
            ArrayList<Variable> allVariables = scope.getAllScopeVariables();
            if (RegexDictionary.commentPattern.matcher(lines[i]).matches()) {
                continue;
            }
            if (RegexDictionary.emptyPattern.matcher(lines[i]).matches()) {
                continue;
            }
            if (RegexDictionary.returnPattern.matcher(lines[i]).matches()) {
                continue;
            }
            if (RegexDictionary.methodCallPattern.matcher(lines[i]).matches()) {
                ValidateMethodCall.isValid(lines[i], methods, allVariables);
                continue;
            }
            if (RegexDictionary.ifWhilePattern.matcher(lines[i]).matches()) {
                Conditional conditionalOb = ValidateIfWhile.validateIfWhile(i, lines, allVariables);
                int ifWhileEndLine = conditionalOb.getScope().endLine;
                phrase(lines, methods, i + 1, ifWhileEndLine, conditionalOb.getScope());
                i = conditionalOb.getScope().endLine;
                continue;
            }
            ValidateVariable.variableValidator(lines[i], scope);

        }

    }
}
