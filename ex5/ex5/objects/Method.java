package oop.ex5.objects;

import java.util.ArrayList;

/**
 * represents a method
 *
 * @author unixraz, maayan.lital
 */
public class Method extends SjavaObject {

    /**
     * variables that are declared in the signature of the function
     */
    public ArrayList<Variable> params;
    private final String name;

    /**
     * method constructor
     *
     * @param params          the parameters that are declared in the signature of the function
     * @param name            the name of the function
     * @param startLine       where the scope of the function starts
     * @param endLine         where it ends
     * @param globalVariables all the global variables that are relevant to the function
     */
    public Method(ArrayList<Variable> params, String name, int startLine, int endLine,
                  ArrayList<Variable> globalVariables) {
        super(startLine, endLine);
        this.getScope().localVariablesAddAll(params);
        this.getScope().setGlobalVariablesPointer(globalVariables);
        this.params = params;
        this.name = name;
    }

    /**
     * looks if a method with this name was declared
     *
     * @param name    the name to look for
     * @param methods list with all declared methods
     * @return the method if it exists, null otherwise
     */
    public static Method getMethod(String name, ArrayList<Method> methods) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    /**
     * getter for the name
     *
     * @return the name of the method
     */
    public String getName() {
        return this.name;
    }
}
