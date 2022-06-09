package filesprocessing.filesfilters;
import filesprocessing.filesexceptions.TypeOneExceptions;
import java.io.File;

/**
 * filter files greater than a certain size
 */
public class SmallerThanFilter extends SingleDoubleFilter {

    /**
     * @param maximum value of size
     * @throws TypeOneExceptions if size is negative
     */
    public SmallerThanFilter(double maximum) throws TypeOneExceptions {
        super(maximum);
    }

    @Override
    public boolean filter(File file){
        return file.length() < size;
    }
}
