package filesprocessing.filesexceptions;

/**
 * class of type 2 exceptions
 */
public class TypeTwoExceptions extends Exception{

    private final String error;

    /**
     * template for the printing method
     */
    private static final String ERROR = "ERROR: ";

    /**
     * default constructor
     */
    public TypeTwoExceptions(String error){
        super();
        this.error = error;
    }

    /**
     * prints an error to the user
     */
    public void printError(){
        System.err.println(ERROR+error);
    }
}
