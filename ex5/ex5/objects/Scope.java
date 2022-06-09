package oop.ex5.objects;

import oop.ex5.tools.GeneralTools;
import oop.ex5.tools.RegexDictionary;
import oop.ex5.validation.ValidateBrackets;

import java.util.ArrayList;


/**
 * a class that represents a Scope which has its relevant variables
 * uses the Facade design pattern
 *
 * @author unixraz, maayan.lital
 */
public class Scope {

    /**
     * maps of all variables and methods available for this scope
     */
    private ArrayList<Variable> globalVariables;
    private ArrayList<Variable> localVariables;

    /**
     * start line index of the scope
     */
    public int startLine;

    /**
     * end line index of the scope
     */
    public int endLine;

    /**
     * default constructor
     */
    public Scope() {
        this.globalVariables = new ArrayList<Variable>();
        this.localVariables = new ArrayList<Variable>();
    }

    /**
     * add a variable to the local variables
     *
     * @param variable the variable to add
     */
    public void addToLocal(Variable variable) {
        localVariables.add(variable);
    }


    /**
     * getter for global vars
     *
     * @return Map of variables
     */
    public ArrayList<Variable> getGlobalVariables() {
        return this.globalVariables;
    }

    /**
     * getter for local vars
     *
     * @return Map of methods
     */
    public ArrayList<Variable> getLocalVariables() {
        return this.localVariables;
    }

    /**
     * returns the scope's end line, that by definition ends with return and '}'.
     *
     * @param lines     lines of the given program file
     * @param startLine the start line of the Scope
     * @return the end line of the scope
     * @throws TypeOneException in case there is no valid ending to tje scope
     */
    public static int getScopeEndLineWithReturn(String[] lines, int startLine)
            throws TypeOneException {
        int suspectedEndLine = startLine + 1;
        while (suspectedEndLine != lines.length - 1) {
            if (RegexDictionary.returnPattern.matcher(lines[suspectedEndLine]).matches() &&
                    RegexDictionary.endBracketPattern.matcher(lines[suspectedEndLine + 1]).matches())
                if (ValidateBrackets.isValid(lines, startLine, suspectedEndLine + 1))
                    return (suspectedEndLine + 1);
            suspectedEndLine++;
        }
        throw new TypeOneException();
    }

    /**
     * returns the scope's end line, that by definition ends with '}'.
     *
     * @param lines     lines of the given program file
     * @param startLine the start line of the Scope
     * @return the end line of the scope
     * @throws TypeOneException in case there is no valid ending to tje scope
     */
    public static int getScopeEndLine(String[] lines, int startLine)
            throws TypeOneException {
        int suspectedEndLine = startLine + 1;
        while (suspectedEndLine != lines.length - 1) {
            if (RegexDictionary.endBracketPattern.matcher(lines[suspectedEndLine]).matches()) {
                if (ValidateBrackets.isValid(lines, startLine, suspectedEndLine))
                    return (suspectedEndLine);
            }
            suspectedEndLine++;
        }
        throw new TypeOneException();
    }

    /**
     * adds all the content in the list to the local variable list
     *
     * @param arr the variables to add
     */
    public void localVariablesAddAll(ArrayList<Variable> arr) {
        localVariables.addAll(arr);
    }

    /**
     * adds all the content in the list to the global variable list
     *
     * @param arr the variables to add
     */
    public void setGlobalVariablesPointer(ArrayList<Variable> arr) {
        this.globalVariables = arr;
    }

    /**
     * set global variables with a given array with deep copy
     *
     * @param arr ArrayList of variables
     */
    public void setGlobalVariables(ArrayList<Variable> arr) {
        this.globalVariables = new ArrayList<Variable>();
        for (Variable variable : arr) {
            this.globalVariables.add(new Variable(variable));
        }
    }

    /**
     * @return an array with all the variables in this scope
     */
    public ArrayList<Variable> getAllScopeVariables() {
        ArrayList<Variable> arr = new ArrayList<Variable>(globalVariables);
        arr.addAll(localVariables);
        return arr;
    }

    /**
     * a function to find and return a local variable with the given name
     *
     * @param name the name to find
     * @return the variable with this name, null if does not exist
     */
    public Variable getLocalVariable(String name) {
        return GeneralTools.findVariable(name, getLocalVariables());
    }

    /**
     * a function to find and return a global variable with the given name
     *
     * @param name the name to find
     * @return the variable with this name, null if does not exist
     */
    public Variable getGlobalVariable(String name) {
        return GeneralTools.findVariable(name, getGlobalVariables());
    }

    /**
     * a function to find and return the last variable with the given name,
     * from all higher hierarchy scopes
     *
     * @param name the name to find
     * @return the variable with this name, null if does not exist
     */
    public Variable getVariable(String name) {
        Variable var = getLocalVariable(name);
        if (var == null) {
            return getGlobalVariable(name);
        }
        return var;
    }

}
