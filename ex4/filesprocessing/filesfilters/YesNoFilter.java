package filesprocessing.filesfilters;

import filesprocessing.filesexceptions.TypeOneExceptions;

/**
 * abstract class for filters that filter files based on
 * if they have or does not have a certain attribute
 */
public abstract class YesNoFilter implements FilterInterface{

    /**
     * constants
     */
    private static final String YES = "YES";
    private static final String NO = "NO";

    /**
     * a boolean represents "yes" or "no" for the filter.
     * true means yes, false means no
     */
    protected final boolean yesNo;

    /**
     * constructor
     * @param yesNo a string that will be used to filter
     * @throws TypeOneExceptions if the string is not "YES" nor "NO"
     */
    YesNoFilter(String yesNo) throws TypeOneExceptions {
        if (!yesNo.equals(NO) && !yesNo.equals(YES)){
            throw new TypeOneExceptions();
        }
        this.yesNo = yesNo.equals(YES);
    }
}
