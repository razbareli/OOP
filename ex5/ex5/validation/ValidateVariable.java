package oop.ex5.validation;

import oop.ex5.objects.Scope;
import oop.ex5.objects.TypeOneException;
import oop.ex5.objects.Variable;
import oop.ex5.tools.BooleanWarping;
import oop.ex5.tools.RegexDictionary;

import java.util.regex.Matcher;

/**
 * a class vor variable validation
 * validates that the line is a legal variable declaration / assignment.
 * creates variable objects if needed
 *
 * @author unixraz, maayan.lital
 */
public class ValidateVariable {

    /**
     * validates the line of the variable. if it's valid, adds a new variable to the scope
     *
     * @param line       the line of the variable
     * @param outerScope the scope to add/look for variables in
     * @throws TypeOneException if the line is not legal
     */
    public static void variableValidator(String line, Scope outerScope) throws TypeOneException {
        if (!line.matches(RegexDictionary.ENDS_WITH_SEMICOLON)) {
            throw new TypeOneException();
        }
        String typeOfVarsInLine = null;
        String[] variables = line.split(RegexDictionary.COMMA);
        BooleanWarping areFinal = new BooleanWarping(false);
        for (int i = 0; i < variables.length; i++) {

            // if it ends with semicolon make sure there is no other variables in line
            handleSemicolon(variables, i);

            //if the variable is new declared:
            if (variables[i].matches(RegexDictionary.NEW_INIT_VAR) ||
                    variables[i].matches(RegexDictionary.TYPE_DECLARED_VAR)) {
                typeOfVarsInLine = newDeclared(outerScope, i, variables, areFinal);
            }

            //if a previously declared variable is initialized
            else if (variables[i].matches(RegexDictionary.OLD_INIT_VAR)) {
                oldInit(outerScope, i, variables, areFinal, typeOfVarsInLine);
            }

            //if only a name is declared
            else if (variables[i].matches(RegexDictionary.NAME_DECLARED_VAR)) {
                onlyNameIsDeclared(i, areFinal);
            }

            //if nothing was matched
            else {
                throw new TypeOneException();
            }
        }
    }

    /**
     * handle the case that only a name of a variable is declared in the line
     *
     * @param i        index of variable
     * @param areFinal BooleanHolder for if the variable is final
     * @throws TypeOneException if the line is not legal
     */
    private static void onlyNameIsDeclared(int i, BooleanWarping areFinal)
            throws TypeOneException {
        if (i == 0) {
            // can't be the only in the line
            throw new TypeOneException();
        }
        if (areFinal.value) {
            //if all variables in the line are final, they must be initialized
            throw new TypeOneException();
        }
    }

    /**
     * handle the situation that a previously declared variable is initialized
     *
     * @param outerScope       the scope to add/look for variables in
     * @param i                index of variable
     * @param variables        variables in the line
     * @param areFinal         BooleanHolder for if the variable is final
     * @param typeOfVarsInLine the type of the variables
     * @throws TypeOneException if the line is not legal
     */
    private static void oldInit(Scope outerScope, int i, String[] variables, BooleanWarping areFinal,
                                String typeOfVarsInLine)
            throws TypeOneException {
        String tempName = extractName(variables[i]);
        Variable declaredVar = outerScope.getVariable(tempName);
        if (i == 0 && declaredVar == null) {
            throw new TypeOneException();
        } else if (i != 0 && declaredVar == null) {
            // assume it was declared in the start of the line
            Variable newVar = variableMaker
                    (typeOfVarsInLine + " " + variables[i], areFinal, true, outerScope);
            outerScope.addToLocal(newVar);
        } else {
            //assume variable exists, check that it is not final, that the value is okay,
            // and initialize the variable
            if (declaredVar.getFinal()) {
                throw new TypeOneException();
            }
            String value = extractValue(variables[i]);
            if (!checkValue(value, declaredVar.getType())) {
                //if the type does not match, treat as a variable
                value = treatAsDeclaredVariable(value, declaredVar.getType(), outerScope);
            }
            declaredVar.setInitialized();
            declaredVar.setValue(value);
        }
    }

    /**
     * declare new variable
     *
     * @param outerScope the scope to add/look for variables in
     * @param i          index of variable
     * @param variables  variables in the line
     * @param areFinal   BooleanHolder for if the variable is final
     * @return type of variable
     * @throws TypeOneException in case of invalidation
     */
    private static String newDeclared(Scope outerScope, int i, String[] variables, BooleanWarping areFinal)
            throws TypeOneException {
        if (i > 0) {
            throw new TypeOneException();
        }
        Variable temp;
        if (variables[i].matches(RegexDictionary.NEW_INIT_VAR)) {
            if (variables[i].matches(RegexDictionary.FINAL_VARIABLE)) { //check if it is final
                areFinal.value = true;
            }
            temp = variableMaker(variables[i], areFinal, true, outerScope);
        } else {
            temp = variableMaker(variables[i], new BooleanWarping(false), false, outerScope);
        }
        return addVariableToScope(temp, outerScope);
    }

    /**
     * if it ends with semicolon make sure there is no other variables in line
     *
     * @param variables variables in the line
     * @param i         index of variable
     * @throws TypeOneException in case of invalidation
     */
    public static void handleSemicolon(String[] variables, int i) throws TypeOneException {
        if (variables[i].matches(RegexDictionary.ENDS_WITH_SEMICOLON)) {
            if (variables.length > i + 1) {
                throw new TypeOneException();
            }
        }
    }


    /**
     * determines the type of the variable
     *
     * @param line the first section in the line
     * @throws TypeOneException if there is no type
     */
    private static String checkType(String line) throws TypeOneException {
        if (line.matches(RegexDictionary.INT_TYPE)) {
            return RegexDictionary.INT;
        } else if (line.matches(RegexDictionary.BOOLEAN_TYPE)) {
            return RegexDictionary.BOOLEAN;
        } else if (line.matches(RegexDictionary.CHAR_TYPE)) {
            return RegexDictionary.CHAR;
        } else if (line.matches(RegexDictionary.DOUBLE_TYPE)) {
            return RegexDictionary.DOUBLE;
        } else if (line.matches(RegexDictionary.STRING_TYPE)) {
            return RegexDictionary.STRING;
        } else {
            throw new TypeOneException();
        }
    }

    /**
     * checks if the value is of the right type
     *
     * @param value the string to check
     * @param type  the type it should be in
     * @return true if it is the right type, false otherwise
     */
    static boolean checkValue(String value, String type) {
        switch (type) {
            case (RegexDictionary.INT):
                return value.matches(RegexDictionary.INT_VALUE);
            case (RegexDictionary.DOUBLE):
                return value.matches(RegexDictionary.DOUBLE_VALUE);
            case (RegexDictionary.CHAR):
                return value.matches(RegexDictionary.CHAR_VALUE);
            case (RegexDictionary.STRING):
                return value.matches(RegexDictionary.STRING_VALUE);
            case (RegexDictionary.BOOLEAN):
                return value.matches(RegexDictionary.BOOLEAN_VALUE);
            default:
                return false;
        }
    }

    /**
     * checks that the name is legal
     *
     * @param name the name to check
     * @return true if legal, false if not
     */
    private static boolean checkName(String name) {
        return name.matches(RegexDictionary.NAME_OF_VARIABLE);
    }


    /**
     * extract the value of the variable
     *
     * @param line the string of the variable
     * @return the value of the variable
     */
    private static String extractValue(String line) {
        int start = line.indexOf(RegexDictionary.EQUALS) + 1;
        int end = line.lastIndexOf(RegexDictionary.SEMICOLON);
        if (end == -1) {
            end = line.length();
        }
        return line.substring(start, end).trim();
    }

    /**
     * extracts the name of the variable in the line
     *
     * @param line the string of the variable
     * @return the name of the variable
     */
    private static String extractName(String line) {
        Matcher matcher = RegexDictionary.typePattern.matcher(line);
        int start;
        int end;
        if (matcher.find()) {
            start = matcher.end();
        } else start = 0;
        if (line.contains(RegexDictionary.EQUALS)) {
            end = line.indexOf(RegexDictionary.EQUALS);
        } else if (line.contains(RegexDictionary.SEMICOLON)) {
            end = line.lastIndexOf(RegexDictionary.SEMICOLON);
        } else end = line.length();
        return line.substring(start, end).trim();
    }

    /**
     * creates a new variable
     *
     * @param line          the string of the variable
     * @param isFinal       if the variable is final
     * @param isInitialized if the variable is init
     * @param outerScope    the outer scope
     * @return a new variable
     * @throws TypeOneException if the line can't represent a new variable
     */
    private static Variable variableMaker
    (String line, BooleanWarping isFinal, boolean isInitialized, Scope outerScope)
            throws TypeOneException {
        String valueOfVar = null;
        String typeOfVar = checkType(line);
        String nameOfVar = extractName(line);
        if (!checkName(nameOfVar)) {
            throw new TypeOneException();
        }
        if (isInitialized) {
            valueOfVar = extractValue(line);
            if (!checkValue(valueOfVar, typeOfVar)) {
                //if the value is not one of the 5 types, treat it as a variable
                valueOfVar = treatAsDeclaredVariable(valueOfVar, typeOfVar, outerScope);
            }
        }
        Variable newVar = new Variable(nameOfVar, typeOfVar, isFinal.value, isInitialized);
        if (isInitialized) {
            newVar.setValue(valueOfVar);
        }
        return newVar;
    }

    /**
     * gets a name of a variable, checks it exists and returns the value of it
     *
     * @param valueOfVar the name of the variable (which is the value of another variable)
     * @param typeOfVar  the type it should be in
     * @param scope      the current scope
     * @return the value that the variable holds
     * @throws TypeOneException if the variable is not initialized, or if its type is not valid
     */
    private static String treatAsDeclaredVariable(String valueOfVar,
                                                  String typeOfVar, Scope scope)
            throws TypeOneException {
        Variable var = scope.getVariable(valueOfVar);
        if (var == null || !var.getInitialized()) {//the variable was never
            // declared or declared but not initialized
            throw new TypeOneException();
        }
        if (var.getInitialized() && var.getValue() == null) {
            return null;
        }
        valueOfVar = var.getValue();
        if (!checkValue(valueOfVar, typeOfVar)) { // check again,
            // with the value of the assign variable
            throw new TypeOneException();
        }
        return valueOfVar;
    }

    /**
     * checks if the variable can be added to the scope
     *
     * @param variable the variable we want to add
     * @param scope    the scope we want to add to
     * @return the type of the new variable
     * @throws TypeOneException if the name is taken already
     */
    private static String addVariableToScope(Variable variable, Scope scope)
            throws TypeOneException {
        if (scope.getLocalVariable(variable.getName()) != null) {
            throw new TypeOneException();
        }
        scope.addToLocal(variable);
        return variable.getType();
    }

}
