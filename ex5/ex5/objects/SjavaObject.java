package oop.ex5.objects;

/**
 * abstract class for methods, if/while and variables
 *
 * @author unixraz, maayan.lital
 */
public abstract class SjavaObject {

    /**
     * the scope obj of the SjavaObject
     */
    private final Scope scope;

    /**
     * SjavaObject constructor
     * init' the number of line where the actual scope of the object starts\ends
     *
     * @param startLine start line index of the scope
     * @param endLine   end line index of the scope
     */
    public SjavaObject(int startLine, int endLine) {
        this.scope = new Scope();
        scope.startLine = startLine;
        scope.endLine = endLine;
    }

    /**
     * getter for the scope
     *
     * @return the scope
     */
    public Scope getScope() {
        return scope;
    }

}
