package filesprocessing.filesorders;

import java.io.File;
import java.util.ArrayList;

/**
 * abstract class for orders
 * includes one sort method
 */
public abstract class OrderSort {

    /**
     * boolean variable for the reverse
     * true if the order should be reversed, false if not
     */
    public boolean reverse;

    /**
     * constructor
     * @param reverse boolean variable for the reverse
     */
    public OrderSort(boolean reverse){
        this.reverse = reverse;
    }

    /**
     * copares 2 files in relation to a certain property
     * @param f1 first file
     * @param f2 second file
     * @return the value 0 if f1 == f2; a value less than 0 if f1 < f2;
     * and a value greater than 0 if f1 > f2
     */
    public abstract int compare(File f1, File f2);

    /**
     * sort algorithm
     * @param files array list with files to sort
     */
    public abstract void sort(ArrayList<File> files);
}
