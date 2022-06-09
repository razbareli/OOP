package oop.ex5.objects;

/**
 * a class for conditional scopes (if and while)
 * extends SjavaObject
 *
 * @author unixraz, maayan.lital
 */
public class Conditional extends SjavaObject {

    /**
     * constructor
     *
     * @param startLine end line index of the conditional scope
     * @param endLine   start line index  of the conditional scope
     */
    public Conditional(int startLine, int endLine) {
        super(startLine, endLine);
    }
}
