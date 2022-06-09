package filesprocessing.filesfilters;

import filesprocessing.filesexceptions.TypeOneExceptions;

/**
 * abstract class for filters using single
 * number representing file size, to filter the files by
 */
public abstract class SingleDoubleFilter implements FilterInterface{


    /**
     * the size to filter by, in bytes
     */
    protected final double size;

    /**
     * constructor
     * @param size the size of the file
     * @throws TypeOneExceptions if the size is negative
     */
    SingleDoubleFilter(double size) throws TypeOneExceptions {
        if (size < 0){
            throw new TypeOneExceptions();
        }
        this.size = size * BYTES_CONVERT;
    }

}
