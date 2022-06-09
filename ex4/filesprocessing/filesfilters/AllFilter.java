package filesprocessing.filesfilters;

import java.io.File;

/**
 * all files get true with this filter
 */
public class AllFilter implements FilterInterface{


    @Override
    public boolean filter(File file) {
        return true;
    }
}
