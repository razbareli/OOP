package filesprocessing.filesfilters;

/**
 * abstract class for a filter that receives a string
 * and filters the file based on that string
 */
public abstract class StringFilter implements FilterInterface{

    /**
     * the string the filter will use to filter the files
     */
    protected final String str;

    /**
     * constructor
     * @param str the string the filter will use to filter the file
     */
    protected StringFilter(String str) {
        this.str = str;
    }

}
