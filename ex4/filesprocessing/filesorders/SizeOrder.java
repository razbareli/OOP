package filesprocessing.filesorders;

import filesprocessing.FilesUtilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Sort files by file type, going from ’a’ to ’z’
 */
public class SizeOrder extends OrderSort {

    /**
     * constructor
     * @param reverse boolean variable for the reverse
     */
    public SizeOrder(boolean reverse) {
        super(reverse);
    }

    @Override
    public int compare(File f1, File f2) {
        if (!SizeOrder.super.reverse){
            return (Long.compare(f1.length(), f2.length()));
        }else{
            return (Long.compare(f2.length(), f1.length()));
        }
    }

    @Override
    public void sort(ArrayList<File> files) {
        FilesUtilities.quickSort(files, 0, files.size()-1, this);
        FilesUtilities.blockAbsSort(files, this);
    }
}
