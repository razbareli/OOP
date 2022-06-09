package oop.ex5.validation;

import oop.ex5.objects.Method;
import oop.ex5.objects.TypeOneException;
import oop.ex5.objects.Variable;
import oop.ex5.tools.GeneralTools;
import oop.ex5.tools.RegexDictionary;

import java.util.ArrayList;

/**
 * validates that a call for a method is valid
 *
 * @author unixraz, maayan.lital
 */
public class ValidateMethodCall {

        final static private int LEGAL_PARTS_SPLIT_BY_CLOSE = 2;
    final static private int PART_OF_FUNC_NAME = 0;

    /**
     * validates that a call for a method is valid
     *
     * @param line           the line of the call
     * @param methods        array list of the given program file's methods objects.
     * @param ExistVariables exist variable that the method can refer to.
     * @throws TypeOneException in case of invalid call
     */
    public static void isValid(String line, ArrayList<Method> methods, ArrayList<Variable> ExistVariables)
            throws TypeOneException {
        String[] parts = line.split(RegexDictionary.END_ROUND_BRACKET);
        if (parts.length != LEGAL_PARTS_SPLIT_BY_CLOSE) {
            throw new TypeOneException();
        }
        String name = parts[PART_OF_FUNC_NAME].trim();
        Method method = Method.getMethod(name, methods);
        if (method != null) {
            checkMethodParams(method, line, ExistVariables);
            return;
        }
        throw new TypeOneException();
    }

    /**
     * split the given line to the method's params
     *
     * @param method the method the line called to
     * @param line   the line of the call
     * @return params String array
     * @throws TypeOneException in case of invalid call
     */
    private static String[] splitMethodParam(Method method, String line)
            throws TypeOneException {
        String parametersString = line.substring
                (line.indexOf(RegexDictionary.OPEN_ROUND_BRACKET) + 1,
                        line.indexOf(RegexDictionary.CLOSE_ROUND_BRACKET));
        if (method.params.size() == 0 && parametersString.equals(RegexDictionary.EMPTY_STRING)) {
            return null;
        }
        if (parametersString.equals(RegexDictionary.EMPTY_STRING)) {
            throw new TypeOneException();
        }
        String[] paramsList = parametersString.split(RegexDictionary.COMMA);
        if (method.params.size() != paramsList.length) {
            throw new TypeOneException();
        }
        return paramsList;
    }

    /**
     * validates the given param to the method are valid
     *
     * @param method         the method the line called to
     * @param line           the line of the call
     * @param ExistVariables exist variable that the method can refer to.
     * @throws TypeOneException in case of invalid call
     */
    private static void checkMethodParams(Method method, String line, ArrayList<Variable> ExistVariables)
            throws TypeOneException {
        String[] paramsList = splitMethodParam(method, line);
        if (paramsList == null) {
            return;
        }
        for (int i = 0; i < paramsList.length; i++) {
            String param = paramsList[i];
            param = param.replaceAll(RegexDictionary.SPACE, RegexDictionary.EMPTY_STRING);
            Variable foundVariable = GeneralTools.findVariable(param, ExistVariables);
            if (foundVariable != null) {
                checkParamValid(foundVariable, method.params.get(i));
                continue;
            }
            if (!ValidateVariable.checkValue(param, method.params.get(i).getType())) {
                throw new TypeOneException();
            }

        }
    }

    /**
     * determine if a given param to the method is valid
     *
     * @param foundVariable given param to the method
     * @param variable      the expected param to the program
     * @throws TypeOneException in case of invalid given param
     */
    private static void checkParamValid(Variable foundVariable, Variable variable)
            throws TypeOneException {
        if (foundVariable.getType().equals(variable.getType()) && foundVariable.getInitialized())
            return;
        throw new TypeOneException();
    }

}
