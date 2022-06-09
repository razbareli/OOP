package oop.ex5.validation;

import oop.ex5.objects.Conditional;
import oop.ex5.objects.Scope;
import oop.ex5.objects.TypeOneException;
import oop.ex5.objects.Variable;
import oop.ex5.tools.GeneralTools;
import oop.ex5.tools.RegexDictionary;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * a class for validation of if/while scopes.
 * generates conditional objects
 *
 * @author unixraz, maayan.lital
 */
public class ValidateIfWhile {

    /**
     * validates that the scope is legal according to the rules
     * if it is, return a new conditional object
     *
     * @param startLine the line number where the scope begins
     * @param lines     the lines of the file
     * @param variables the variables in the scope
     * @return a new conditional object
     * @throws TypeOneException if one of the lines in the scope is not legal
     */
    public static Conditional validateIfWhile(int startLine, String[] lines,
                                              ArrayList<Variable> variables)
            throws TypeOneException {
        //validate the condition in the if/while statement
        String signatureLine = lines[startLine];
        String parametersString = signatureLine.substring
                (signatureLine.indexOf(RegexDictionary.OPEN_ROUND_BRACKET) + 1,
                        signatureLine.indexOf(RegexDictionary.CLOSE_ROUND_BRACKET)).trim();
        if (!validateCondition(parametersString, variables)) {
            throw new TypeOneException();
        }
        //validates the rest of the lines, inside the if/while statements
        int endLine = Scope.getScopeEndLine(lines, startLine);
        Conditional conditionalObj = new Conditional(startLine, endLine);
        conditionalObj.getScope().setGlobalVariablesPointer(variables);
        return conditionalObj;
    }

    /**
     * validates that the argument in the condition is valid
     *
     * @param line      the condition of the if/while statement
     * @param variables the variables iun this scope
     * @return true or false
     */
    public static boolean validateCondition(String line, ArrayList<Variable> variables) {
        //split the conditional argument to sub arguments
        ArrayList<String> conditions = new ArrayList<String>();
        Matcher matcher = RegexDictionary.orAndPattern.matcher(line);
        int start = 0;
        while (matcher.find()) {
            conditions.add(line.substring(start, matcher.start()).trim());
            start = matcher.end();
        }
        return booleanCases(conditions, line, start, variables);
    }

    /**
     * validates by boolean cases
     *
     * @param conditions all the condition in the condition of the if/while statement
     * @param line       the condition of the if/while statement
     * @param start      the start for the substring
     * @param variables  the variables iun this scope
     * @return true or false
     */
    private static boolean booleanCases(ArrayList<String> conditions, String line, int start,
                                        ArrayList<Variable> variables) {
        conditions.add(line.substring(start).trim());
        for (String str : conditions) {
            //check if the argument is bool/int/double
            if (str.matches(RegexDictionary.BOOLEAN_VALUE)) {
                continue;
            }
            //if not, consider it a variable and look if it exists and of the correct type
            if (!validateBoolean(GeneralTools.findVariable(str, variables))) {
                return false;
            }
        }
        return true;
    }


    /**
     * validates that the variable was initialized and that it's a conditional
     *
     * @param var the variable
     * @return true ot false
     */
    private static boolean validateBoolean(Variable var) {
        if (var == null) {
            return false;
        }
        if (!var.getInitialized()) {
            return false;
        }
        return (var.getType().equals(RegexDictionary.BOOLEAN) || var.getType().equals(RegexDictionary.INT)
                || var.getType().equals(RegexDictionary.DOUBLE));
    }

}
