package filesprocessing.filesfilters;

import java.io.File;

/**
 * filters files that starts a certain string
 */
public class SuffixFilter extends FileFilter {


    /**
     * constructor
     * @param str the string the filter will use to filter the files
     */
    public SuffixFilter(String str) {
        super(str);
    }

    /**
     * @param file the file to filter
     * @return true if the filename ends with str,
     * false if not
     */
    @Override
    public boolean filter(File file) {
        return file.getName().endsWith(str);
    }
}