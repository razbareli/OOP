package filesprocessing.filesfilters;

import filesprocessing.filesexceptions.TypeOneExceptions;

import java.io.File;

/**
 * filters a file based on if it is hidden or not
 */
public class HiddenFilter extends YesNoFilter{
    /**
     * constructor
     * @param yesNo a string that will be used to filter
     * @throws TypeOneExceptions if the string is not "YES" nor "NO"
     */
    public HiddenFilter(String yesNo) throws TypeOneExceptions {
        super(yesNo);
    }

    /**
     * @param file the file to filter
     * @return true if the file is executable, false otherwise
     */
    @Override
    public boolean filter(File file) {
        if (yesNo){
            return file.isHidden();
        }else{
            return !file.isHidden();
        }
    }
}
