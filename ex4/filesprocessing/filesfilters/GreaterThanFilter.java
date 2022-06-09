package filesprocessing.filesfilters;
import filesprocessing.filesexceptions.TypeOneExceptions;
import java.io.File;

/**
 * filter files greater than a certain size
 */
public class GreaterThanFilter extends SingleDoubleFilter {

    /**
     *
     * @param minimum value of size
     * @throws TypeOneExceptions if size is negative
     */
    public GreaterThanFilter(double minimum) throws TypeOneExceptions {
        super(minimum);
    }

    @Override
    public boolean filter(File file){
        return file.length() > size;
        }
    }

