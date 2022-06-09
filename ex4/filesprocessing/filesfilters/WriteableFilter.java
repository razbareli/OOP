package filesprocessing.filesfilters;

import filesprocessing.filesexceptions.TypeOneExceptions;

import java.io.File;

/**
 * filters a file based on if it is writeable or not
 */
public class WriteableFilter extends YesNoFilter{

    /**
     * constructor
     * @param yesNo a string that will be used to filter
     * @throws TypeOneExceptions if the string is not "YES" nor "NO"
     */
    public WriteableFilter(String yesNo) throws TypeOneExceptions {
        super(yesNo);
    }

    /**
     * @param file the file to filter
     * @return true if the file is writeable, false otherwise
     */
    @Override
    public boolean filter(File file) {
        if (yesNo){
            return file.canWrite();
        }else{
            return !file.canWrite();
        }
    }
}
