package filesprocessing.filesfilters;

import filesprocessing.filesexceptions.TypeOneExceptions;

import java.io.File;

/**
 * filters files that have a size between two values
 */
public class BetweenFilter implements FilterInterface{

    /**
     * upper limit size
     */
    private final double upper;

    /**
     * lower limit size
     */
    private final double lower;

    /**
     * constructor
     * @param lower size limit
     * @param upper size limit
     */
    public BetweenFilter(double lower, double upper) throws TypeOneExceptions {
        if (upper < lower || upper < 0 || lower < 0){
            throw new TypeOneExceptions();
        }
        this.lower = lower * BYTES_CONVERT;
        this.upper = upper * BYTES_CONVERT;
    }

    @Override
    public boolean filter(File file){
        return file.length() >= lower && file.length() <= upper;
        }
    }
