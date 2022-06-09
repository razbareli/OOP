package filesprocessing.filesorders;

import filesprocessing.FilesUtilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Sort files by file size, going from smallest to largest
 */
public class TypeOrder extends OrderSort {

    /**
     * constructor
     *
     * @param reverse boolean variable for the reverse
     */
    public TypeOrder(boolean reverse) {
        super(reverse);
    }

    @Override
    public int compare(File f1, File f2) {
        String type1 = FilesUtilities.typeOfFile(f1.getName());
        String type2 = FilesUtilities.typeOfFile(f2.getName());
        if (!TypeOrder.super.reverse) {
            return (type1.compareTo(type2));
        } else {
            return (type2.compareTo(type1));
        }
    }

    @Override
    public void sort(ArrayList<File> files) {
        FilesUtilities.quickSort(files, 0, files.size() - 1, this);
        FilesUtilities.blockAbsSort(files, this);
    }
}

