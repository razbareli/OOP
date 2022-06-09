package oop.ex5.validation;

import oop.ex5.objects.Method;
import oop.ex5.objects.Scope;
import oop.ex5.objects.TypeOneException;
import oop.ex5.objects.Variable;
import oop.ex5.tools.RegexDictionary;

import java.util.regex.Matcher;
import java.util.ArrayList;

/**
 * validate method signature and creates new instance of method
 *
 * @author unixraz, maayan.lital
 */
public class ValidateMethodSignature {

    /**
     * parts of split
     */
    public static final int
            SECOND_PART = 1,
            FIRST_PART = 0;

    /**
     * checks the method's validation and creates new instance of method
     *
     * @param lines           array list of the given program file's methods objects.
     * @param startLine       the start line index of the method
     * @param methods         array list of the given program file's methods objects.
     * @param globalVariables the global variables (from methods perspective)
     * @return end line of the method
     * @throws TypeOneException in case of invalid method signature line
     */
    public static int isValid(String[] lines, int startLine, ArrayList<Method> methods,
                              ArrayList<Variable> globalVariables)
            throws TypeOneException {
        validateSignature(lines, startLine);
        ArrayList<Variable> params = getMethodParams(lines[startLine]);
        String methodName = getMethodName(lines, startLine);
        int endLine = Scope.getScopeEndLineWithReturn(lines, startLine);
        ValidateBrackets.validate(lines, startLine, endLine);
        Method method = new Method(params, methodName, startLine, endLine, globalVariables);
        methods.add(method);
        return endLine;
    }

    /**
     * returns ArrayList of the params of the method
     *
     * @param signatureLine the method signature line
     * @return ArrayList of the params of the method
     * @throws TypeOneException in case of invalid method signature line
     */
    private static ArrayList<Variable> getMethodParams(String signatureLine) throws TypeOneException {
        ArrayList<Variable> validParams = new ArrayList<>();
        ArrayList<String> paramsNames = new ArrayList<>();

        String parametersString = signatureLine.substring
                (signatureLine.indexOf(RegexDictionary.OPEN_ROUND_BRACKET) + 1,
                        signatureLine.indexOf(RegexDictionary.CLOSE_ROUND_BRACKET));
        if (parametersString.equals(RegexDictionary.EMPTY_STRING)) {
            return validParams;
        }
        String[] paramsList = parametersString.split(RegexDictionary.COMMA);
        insertParams(paramsList, paramsNames, validParams);
        return validParams;
    }

    /**
     * insert the valid params to the valid params list
     *
     * @param paramsList  suspected params to check validation
     * @param paramsNames list of valid params name
     * @param validParams list of valid params
     * @throws TypeOneException in case of invalid param
     */
    public static void insertParams(String[] paramsList, ArrayList<String> paramsNames,
                                    ArrayList<Variable> validParams) throws TypeOneException {
        for (String param : paramsList) {
            if (RegexDictionary.intFuncPattern.matcher(param).matches()) {
                insertParam(param, validParams, RegexDictionary.INT, paramsNames);
                continue;
            }
            if (RegexDictionary.booleanFuncPattern.matcher(param).matches()) {
                insertParam(param, validParams, RegexDictionary.BOOLEAN, paramsNames);
                continue;
            }
            if (RegexDictionary.doubleFuncPattern.matcher(param).matches()) {
                insertParam(param, validParams, RegexDictionary.DOUBLE, paramsNames);
                continue;
            }
            if (RegexDictionary.charFuncPattern.matcher(param).matches()) {
                insertParam(param, validParams, RegexDictionary.CHAR, paramsNames);
                continue;
            }
            if (RegexDictionary.stringFuncPattern.matcher(param).matches()) {
                insertParam(param, validParams, RegexDictionary.STRING, paramsNames);
                continue;
            }
            throw new TypeOneException();
        }
    }

    /**
     * creates new variable for the param and insert the param to validParams
     *
     * @param param       param to insert
     * @param validParams list of valid params
     * @param type        the type of the param
     * @param paramsNames list of valid params name
     * @throws TypeOneException in case of invalid param
     */
    private static void insertParam(String param, ArrayList<Variable> validParams, String type,
                                    ArrayList<String> paramsNames)
            throws TypeOneException {
        param = param.replaceAll(RegexDictionary.SPACE, RegexDictionary.EMPTY_STRING);
        boolean isFinal = param.startsWith(RegexDictionary.FINAL);
        String[] parts = param.split(type);
        String name = parts[SECOND_PART];
        if (paramsNames.contains(name)) {
            throw new TypeOneException();
        }
        paramsNames.add(name);
        validParams.add(new Variable(name, type, isFinal, true));
    }

    /**
     * validates that the method signature is valid with her name, params, etc.
     *
     * @param lines     array list of the given program file's methods objects.
     * @param startLine the start line index of the method
     * @throws TypeOneException in case of invalid param
     */
    public static void validateSignature(String[] lines, int startLine) throws TypeOneException {
        String signatureLine = lines[startLine];
        if (!RegexDictionary.methodSignaturePattern.matcher(signatureLine).matches()) {
            throw new TypeOneException();
        }
        String parameters = signatureLine.substring
                (signatureLine.indexOf(RegexDictionary.OPEN_ROUND_BRACKET) + 1,
                        signatureLine.indexOf(RegexDictionary.CLOSE_ROUND_BRACKET));
        if (parameters.equals(RegexDictionary.EMPTY_STRING)) {
            return;
        }
        Matcher matcherMethodParamsPattern = RegexDictionary.methodParamsPattern.matcher(parameters);
        if (!matcherMethodParamsPattern.matches()) {
            throw new TypeOneException();
        }
    }

    /**
     * extract the name of the method from the signature
     *
     * @param lines     array list of the given program file's methods objects.
     * @param startLine the start line index of the method
     * @return the name of the method
     */
    public static String getMethodName(String[] lines, int startLine) {
        String signatureLine = lines[startLine];
        String[] partsAfterVoid = signatureLine.split(RegexDictionary.FUNC_REGEX);
        String[] partsBeforeParams = partsAfterVoid[SECOND_PART].split(RegexDictionary.END_ROUND_BRACKET);
        return partsBeforeParams[FIRST_PART].trim();
    }


}
