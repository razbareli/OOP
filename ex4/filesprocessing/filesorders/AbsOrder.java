package filesprocessing.filesorders;

import filesprocessing.FilesUtilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Sort files by absolute name (using File.getAbsolutePath() ), going from ’a’ to ’z’
 */
public class AbsOrder extends OrderSort{

    /**
     * constructor
     * @param reverse boolean variable for the reverse
     */
    public AbsOrder(boolean reverse) {
        super(reverse);
    }

    @Override
    public int compare(File f1, File f2) {
        if (!AbsOrder.super.reverse) {
            return (f1.getAbsolutePath().compareTo(f2.getAbsolutePath()));
        } else {
            return (f2.getAbsolutePath().compareTo(f1.getAbsolutePath()));
        }
    }

    @Override
    public void sort(ArrayList<File> files) {
        FilesUtilities.quickSort(files, 0, files.size()-1, this);
    }


}
