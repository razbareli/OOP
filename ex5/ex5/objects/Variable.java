package oop.ex5.objects;

/**
 * this class represents a variable
 *
 * @author unixraz, maayan.lital
 */
public class Variable {
    /**
     * the type of the variable
     */
    private final String type;

    /**
     * the name of the variable
     */
    private final String name;

    /**
     * boolean that represents if the variable is initialised or not
     */
    private boolean isInitialized;

    /**
     * boolean that represents if the variable is final or not
     */
    private final boolean isFinal;

    /**
     * the value that the variable holds
     * not initialized variables have null
     */
    private String value;

    /**
     * constructor
     *
     * @param name          name of the var
     * @param type          type of the var
     * @param isFinal       is the var final?
     * @param isInitialized is the var initialized?
     */
    public Variable(String name, String type, boolean isFinal, boolean isInitialized) {
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
        this.isInitialized = isInitialized;
    }

    /**
     * copy constructor
     *
     * @param variable the variable to copy
     */
    public Variable(Variable variable) {
        this.name = variable.name;
        this.type = variable.type;
        this.isFinal = variable.isFinal;
        this.isInitialized = variable.isInitialized;
        this.value = variable.value;
    }

    /**
     * name getter
     *
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * initialized getter
     *
     * @return if the var is initialized
     */
    public boolean getInitialized() {
        return isInitialized;
    }

    /**
     * final getter
     *
     * @return if the variable is final
     */
    public boolean getFinal() {
        return isFinal;
    }

    /**
     * value getter
     *
     * @return the value of the variable
     */
    public String getValue() {
        return value;
    }

    /**
     * type getter
     *
     * @return the type of the variable
     */
    public String getType() {
        return type;
    }

    /**
     * setter function for value
     *
     * @param value value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * setter function for initialized
     * makes isInitialized true
     */
    public void setInitialized() {
        this.isInitialized = true;
    }
}
