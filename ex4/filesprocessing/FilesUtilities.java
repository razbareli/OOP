package filesprocessing;

import filesprocessing.filesorders.AbsOrder;
import filesprocessing.filesorders.OrderSort;

import java.io.*;
import java.util.ArrayList;

/**
 * utilities class for file processing
 * includes a sorting algorithm, type of file checker and
 * a method that puts all files in the directory to an array
 */
public class FilesUtilities {

    /**
     * quick sort algorithm
     * @param filesArr array to sort
     * @param low starting index
     * @param high ending index
     * @param order the order of sorting we want to use
     */
    public static void quickSort(ArrayList<File> filesArr, int low, int high, OrderSort order){
        if (low < high){
            int partitionIndex = partition(filesArr, low, high, order);
            quickSort(filesArr, low, partitionIndex - 1, order);
            quickSort(filesArr, partitionIndex +1, high, order);
        }
    }

    /**
     * swap function for quick sort. swaps the value in
     * the given 2 indexes
     * @param filesArr array of elements
     * @param i index
     * @param j index
     */
    private static void swap(ArrayList<File> filesArr, int i, int j)
    {
        File temp = filesArr.get(i);
        filesArr.set(i, filesArr.get(j));
        filesArr.set(j, temp);
    }

    /**
     * partition function for quick sort
     * @param filesArr array of files
     * @param low smallest value index
     * @param high largest value index
     * @param order the order of sorting we want to use
     * @return the index of the partition
     */
    private static int partition(ArrayList<File> filesArr, int low, int high, OrderSort order)
    {
        File pivot = filesArr.get(high);
        int i = (low - 1);
        for(int j = low; j <= high - 1; j++)
        {
            if (order.compare(filesArr.get(j), pivot) < 0) {
                i++;
                swap(filesArr, i, j);
            }
        }
        swap(filesArr, i + 1, high);
        return (i + 1);
    }

    /**
     * function for abs sorting for block that have the same value
     * when sorting using type or size
     * @param filesArr array of blocks to sort
     * @param order the original order instance of type / size
     */
    public static void blockAbsSort(ArrayList<File> filesArr, OrderSort order){
        int blockLen = 0;
        int i = 0;
        int j = 1;
        while (i < filesArr.size()){
            while (j < filesArr.size() && order.compare(filesArr.get(i),filesArr.get(j)) == 0){
                blockLen++;
                j++;
            }
            if (blockLen > 0){
                FilesUtilities.quickSort(filesArr, i, j-1, new AbsOrder(order.reverse));
            }
            i = j;
            j = i + 1;
        }
    }


    /**
     * @param fileName the full name of the file
     * @return a string that represents the type of the file
     */
    public static String typeOfFile(String fileName){
        int index = fileName.lastIndexOf('.');
        if (index == -1 || index == 0){
            return "";
        }else{
            return fileName.substring(index+1);
        }
    }

    /**
     * returns an array of all files in the directory
     * @param path path to directory
     * @return array of all files in the directory
     */
    public static ArrayList<File> FilesToArr(String path) {
        ArrayList<File> ans = new ArrayList<>();
        File temp = new File(path);
        File[] files = temp.listFiles();
        assert files != null;
        for (File curr : files) {
            if (curr.isFile()) {
                ans.add(curr);
            }
        }
        return ans;
    }



}
