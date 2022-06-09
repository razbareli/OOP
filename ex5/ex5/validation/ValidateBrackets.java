package oop.ex5.validation;

import oop.ex5.objects.TypeOneException;
import oop.ex5.tools.GeneralTools;
import oop.ex5.tools.RegexDictionary;

import java.util.Stack;

/**
 * validate that given lines contains valid appearance of curled brackets.
 *
 * @author unixraz, maayan.lital
 */
public class ValidateBrackets {

    /**
     * checks if given lines contains valid appearance of curled brackets.
     *
     * @param lines     the lines of the given program file
     * @param startLine the start line index of the line we would like to check from
     * @param endLine   the end line index
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String[] lines, int startLine, int endLine) {
        Stack<String> stack = new Stack<String>();
        String[] relevantLines = GeneralTools.getSliceOfArray(lines, startLine, endLine + 1);
        for (String line : relevantLines) {
            line = line.replaceAll(RegexDictionary.SPACE, RegexDictionary.EMPTY_STRING);
            if (line.endsWith(RegexDictionary.OPEN_CURLED_BRACKET)) {
                stack.push(RegexDictionary.OPEN_CURLED_BRACKET);
                continue;
            }
            if (line.endsWith(RegexDictionary.CLOSE_CURLED_BRACKET)) {
                if (stack.peek().equals(RegexDictionary.OPEN_CURLED_BRACKET)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    /**
     * checks if given lines contains valid appearance of curled brackets.
     *
     * @param lines     the lines of the given program file
     * @param startLine the start line index of the line we would like to check from
     * @param endLine   the end line index
     * @throws TypeOneException in case the curled brackets' appearance is illegal.
     */
    public static void validate(String[] lines, int startLine, int endLine)
            throws TypeOneException {
        if (!isValid(lines, startLine, endLine)) {
            throw new TypeOneException();
        }
    }


}
