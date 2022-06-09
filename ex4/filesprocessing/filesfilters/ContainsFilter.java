package filesprocessing.filesfilters;

import java.io.File;

/**
 * filters files that contains a certain string
 */
public class ContainsFilter extends StringFilter{


    /**
     * constructor
     * @param str the string the filter will use to filter the files
     */
    public ContainsFilter(String str) {
        super(str);
    }

    /**
     * @param file the file to filter
     * @return true if the filename contains str
     * false if not
     */
    @Override
    public boolean filter(File file) {
        return file.getName().contains(str);
    }
}
