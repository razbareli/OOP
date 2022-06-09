package filesprocessing.filesexceptions;

import static java.lang.Integer.parseInt;

/**
 * class of type 1 exceptions

 */
public class TypeOneExceptions extends Exception{

    /**
     * template for the printing method
     */
    private static final String WARNING = "Warning in line ";

    /**
     * default constructor
     */
    public TypeOneExceptions() {
        super();
    }

//    /**
//     * prints a warning to the user
//     */
//    public void printWarning(Integer lineNumber){
//
//        System.err.println(WARNING+lineNumber.toString());
//    }




}
