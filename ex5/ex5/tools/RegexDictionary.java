package oop.ex5.tools;

import java.util.regex.Pattern;

/**
 * regexes for program
 *
 * @author unixraz, maayan.lital
 */
public class RegexDictionary {

    /**
     * reserved keywords and general symbols
     */
    public static final String
            INT = "int",
            DOUBLE = "double",
            STRING = "String",
            BOOLEAN = "boolean",
            CHAR = "char",
            VOID = "void",
            IF = "if",
            WHILE = "while",
            TRUE = "true",
            FALSE = "false",
            RETURN = "return",
            FINAL = "final",
            COMMA = ",",
            EQUALS = "=",
            SEMICOLON = ";";

    /**
     * important regexes
     */
    public static final String
            LEGAL_NAME_VAR = "(_[_a-zA-Z0-9]|[a-zA-Z])[_0-9a-zA-Z]*",
            LEGAL_TYPE_VAR = "(int|String|boolean|char|double)",
            FINAL_VAR = "(\\s*" + FINAL + "\\s+)";

    /**
     * regexes for variables
     */
    public static final String
            FINAL_VARIABLE = "^" + FINAL_VAR + ".*",
            NEW_INIT_VAR = "^" + FINAL_VAR + "?\\s*" + LEGAL_TYPE_VAR + "\\s*" +
                    LEGAL_NAME_VAR + "(\\s*)" + EQUALS + "(\\s*.+\\s*)$",
            TYPE_DECLARED_VAR = "^\\s*" + LEGAL_TYPE_VAR + "\\s*"
                    + LEGAL_NAME_VAR + "(\\s*" + SEMICOLON + "?\\s*)$",
            OLD_INIT_VAR = "^\\s*" + LEGAL_NAME_VAR + "(\\s*)" + EQUALS + "(\\s*.+\\s*)",
            NAME_DECLARED_VAR = "^\\s*" + LEGAL_NAME_VAR + "(\\s*" + SEMICOLON + "?\\s*)$",
            NAME_OF_VARIABLE = "^" + LEGAL_NAME_VAR + "$",
            TYPE = "\\s*" + LEGAL_TYPE_VAR + "\\s+",
            ENDS_WITH_SEMICOLON = "(.*" + SEMICOLON + "\\s*)$";

    /**
     * regex for type
     */
    public static final String
            BOOLEAN_TYPE = ".*" + BOOLEAN + ".*",
            INT_TYPE = ".*" + INT + ".*",
            CHAR_TYPE = ".*" + CHAR + ".*",
            DOUBLE_TYPE = ".*" + DOUBLE + ".*",
            STRING_TYPE = ".*" + STRING + ".*";

    /**
     * regex for matching a value with a type
     */
    public static final String
            INT_VALUE = "(^[-]?[0-9]\\d*$)",
            BOOLEAN_VALUE = "(^" + TRUE + "|^" + FALSE + "|[-]?[0-9]\\d*.[-]?[0-9]\\d*|[-]?[0-9]\\d*)",
            CHAR_VALUE = "^'.'$", DOUBLE_VALUE = "([-]?[0-9]\\d*.[-]?[0-9]\\d*|[-]?[0-9]\\d*)",
            STRING_VALUE = "^\".*\"$";

    /**
     * regexes for methods
     */
    public static final String
            RETURN_REGEX = "^\\s*" + RETURN + "\\s*;\\s*$",
            END_BRACKET = "^(\\s*)?[}](\\s*)?$",
            EMPTY_REGEX = "^\\s*$",
            FUNC_REGEX = "^\\s*" + VOID + "\\s*",
            COMMENT_REGEX = "^[\\/][\\/].*$",
            END_ROUND_BRACKET = "[(]",
            INT_REGEX = "^(\\s*)?(" + FINAL + " )?(\\s*)?" + INT + "(\\s*)?(\\w*)?(\\s*)?$",
            DOUBLE_REGEX = "^(\\s*)?(" + FINAL + " )?(\\s*)?" + DOUBLE + "(\\s*)?(\\w*)?(\\s*)?$",
            STRING_REGEX = "^(\\s*)?(" + FINAL + " )?(\\s*)?" + STRING + "(\\s*)?(\\w*)?(\\s*)?$",
            BOOLEAN_REGEX = "^(\\s*)?(" + FINAL + " )?(\\s*)?" + BOOLEAN + "(\\s*)?(\\w*)?(\\s*)?$",
            CHAR_REGEX = "^(\\s*)?(" + FINAL + " )?(\\s*)?" + CHAR + "(\\s*)?(\\w*)?(\\s*)?$",
            METHOD_SIGNATURE_REGEX = "^\\s*" + VOID + "\\s+[a-zA-Z]\\w*\\s*[(]\\s*.*\\s*[)]" + "\\s*[{]\\s*$",
            METHOD_PARAMS_REGEX = "^\\s*?((" + FINAL + " )?\\s*" + LEGAL_TYPE_VAR +
                    "\\s+([a-z]|_[a-z])\\w*)\\s*(,\\s*((" + FINAL + " )?\\s*" + LEGAL_TYPE_VAR +
                    "\\s+([a-z]|_[a-z])\\w*))*\\s*$",
            METHOD_CALL = "^\\s*\\w+[\\w ,\\t]*[(][.\"'\\w ,\\t]*[)][\\t ]*;(\\s*)$",
            OPEN_CURLED_BRACKET = "{",
            CLOSE_CURLED_BRACKET = "}",
            EMPTY_STRING = "",
            SPACE = "\\s+";

    /**
     * if and while regexes
     */
    public static final String
            IF_WHILE_LINE = "^(\\s*" + WHILE + "|\\s*" + IF + ")\\s*[(].*[)]\\s*([{]\\s*)$",
            OR_AND = "[|]{2}|[&]{2}",
            OPEN_ROUND_BRACKET = "(",
            CLOSE_ROUND_BRACKET = ")";


    /**
     * usable patterns
     */
    public static final Pattern typePattern = Pattern.compile(TYPE),
            returnPattern = Pattern.compile(RETURN_REGEX),
            endBracketPattern = Pattern.compile(END_BRACKET),
            methodPattern = Pattern.compile(FUNC_REGEX),
            emptyPattern = Pattern.compile(EMPTY_REGEX),
            commentPattern = Pattern.compile(COMMENT_REGEX),
            methodSignaturePattern = Pattern.compile(METHOD_SIGNATURE_REGEX),
            methodParamsPattern = Pattern.compile(METHOD_PARAMS_REGEX),
            intFuncPattern = Pattern.compile(INT_REGEX),
            booleanFuncPattern = Pattern.compile(BOOLEAN_REGEX),
            doubleFuncPattern = Pattern.compile(DOUBLE_REGEX),
            charFuncPattern = Pattern.compile(CHAR_REGEX),
            stringFuncPattern = Pattern.compile(STRING_REGEX),
            methodCallPattern = Pattern.compile(METHOD_CALL),
            ifWhilePattern = Pattern.compile(IF_WHILE_LINE),
            orAndPattern = Pattern.compile(OR_AND);

}

