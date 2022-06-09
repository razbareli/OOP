package filesprocessing.filesfilters;

import java.io.File;

/**
 * filters files with the same name as the given one
 */
public class FileFilter extends StringFilter{


    /**
     * constructor
     * @param str the string the filter will use to filter the files
     */
    public FileFilter(String str) {
        super(str);
    }

    /**
     * @param file the file to filter
     * @return true if the filename is equal to str
     * false if not
     */
    @Override
    public boolean filter(File file){
        return file.getName().equals(str);
    }
}
