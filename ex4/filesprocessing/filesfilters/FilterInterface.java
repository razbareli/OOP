package filesprocessing.filesfilters;
import filesprocessing.filesexceptions.TypeOneExceptions;

import java.io.File;

/**
 * an interface for all filters to implement
 * includes one boolean method, that checks a different
 * condition in every filter
 */
public interface FilterInterface {

    /**
     * multiply k-bytes in this constant to get the value
     * in bytes
     */
    long BYTES_CONVERT = 1024;

    /**
     * filters a file according to some condition
     * @param file the file to filter
     * @return true if the file makes the condition,
     * false if not
     */
    boolean filter(File file);
}
