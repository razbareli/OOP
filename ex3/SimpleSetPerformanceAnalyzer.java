import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * measures the run-times requested for the operation in
 * Simple Set
 */
public class SimpleSetPerformanceAnalyzer {

    /**
     * number of iteration for warm up
     */
    private static final int WARM_UP = 70000;

    /**
     * number of iteration to check contains() on linked list
     */
    private static final int LIST_ITER = 7000;

    /**
     * number of sets we are testing
     */
    private static final int NUM_OF_SETS = 5;

    /**
     * identifying numbers for each type of set
     */
    private static final int OPEN = 0;
    private static final int CLOSED = 1;
    private static final int TREE = 2;
    private static final int LIST = 3;
    private static final int HASH = 4;

    /**
     * converts from nano to milliseconds when divided by
     */
    private static final int MILLI_CONVERTER = 1000000;

    /**
     * path to the text files
     */
    private static final String PATH_TO_FILES =
            "C:\\Users\\רז\\IdeaProjects\\ex3\\src\\";

    /**
     * array that holds one instance of each type of set
     */
    private final SimpleSet[] arr = new SimpleSet[NUM_OF_SETS];

    /**
     * array with words from data1 file
     */
    public  String[] data1;

    /**
     * array with words from data2 file
     */
    public  String[] data2;

    /**
     * default constructor
     * reads the 2 files into the 2 array fields
     * generates 5 types of empty sets
     */
    SimpleSetPerformanceAnalyzer(){
        readFiles();
        setFactory();
    }

    /**
     * generates the 5 types of sets into arr
     */
    private void setFactory(){
        OpenHashSet openSet = new OpenHashSet();
        ClosedHashSet closedSet = new ClosedHashSet();
        CollectionFacadeSet treeSet = new CollectionFacadeSet(new TreeSet<String>());
        CollectionFacadeSet linkedList = new CollectionFacadeSet(new LinkedList<String>());
        CollectionFacadeSet hashSet = new CollectionFacadeSet(new HashSet<String>());
        arr[OPEN] = openSet;
        arr[CLOSED] = closedSet;
        arr[TREE] = treeSet;
        arr[LIST] = linkedList;
        arr[HASH] = hashSet;
    }

    /**
     * reads the files and put each word in the array
     */
    private void readFiles(){
        data1  = Ex3Utils.file2array(PATH_TO_FILES+"data1.txt");
        data2 = Ex3Utils.file2array(PATH_TO_FILES+"data2.txt");
    }

    /**
     * runs the addition to set test
     * @param strArr array to test on
     */
    public void addTest(String[] strArr){
        for (int i = 0; i < NUM_OF_SETS; i++) {
            long startTime = System.nanoTime();
            for (String str : strArr){
                arr[i].add(str);
            }
            long endTime =  System.nanoTime() - startTime;
            printTime(i, endTime, false);
        }
    }

    /**
     * runs the contains test
     * @param strArr the array add to the sets
     * @param str the word to find in the set
     */
    public void containsTest(String[] strArr, String str){
        for (int i = 0; i < NUM_OF_SETS; i++) {
            for (String curr : strArr){
                arr[i].add(curr);
            }
            //if the set is not linked list, warm up
            if (i != LIST){
                for (int j = 0; j < WARM_UP; j++) {
                    arr[i].contains(str);
                }
            }
            // decide how many iterations to do
            int iterations = WARM_UP;
            if (i == LIST) {
                iterations = LIST_ITER;
            }
            //start measurement
            long startTime = System.nanoTime();
            for (int j = 0; j < iterations; j++) {
                arr[i].contains(str);
            }
            long endTime =  (System.nanoTime() - startTime) / iterations;
            printTime(i, endTime, true);
        }
    }

    /**
     * printing the test results
     * @param typeOfSet the set we are testing
     * @param nanoSeconds the time it took in nanoseconds
     * @param format true if we want the result printed in nanoseconds
     *               false in we want milliseconds
     */
    private void printTime(int typeOfSet, long nanoSeconds, boolean format){

        switch (typeOfSet) {
            case (OPEN):
                System.out.print("OpenSet: ");
                break;
            case (CLOSED):
                System.out.print("ClosedSet: ");
                break;
            case (TREE):
                System.out.print("TreeSet: ");
                break;
            case (LIST):
                System.out.print("LinkedList: ");
                break;
            case (HASH):
                System.out.print("HashSet: ");
                break;
        }
        if (format) {
            System.out.print(nanoSeconds);
            System.out.println(" nanoseconds");
        }else{
            System.out.print(nanoSeconds/MILLI_CONVERTER);
            System.out.println(" milliseconds");
        }
    }

    /**
     * executes all 6 tests
     * @param args NA
     */
    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer analyzer = new SimpleSetPerformanceAnalyzer();
        System.out.println("Test 1:");
        analyzer.addTest(analyzer.data1);
        System.out.println("Test 2:");
        analyzer.addTest(analyzer.data2);
        System.out.println("Test 3:");
        analyzer.containsTest(analyzer.data1, "hi");
        System.out.println("Test 4:");
        analyzer.containsTest(analyzer.data1, ("-13170890158"));
        System.out.println("Test 5:");
        analyzer.containsTest(analyzer.data2, ("23"));
        System.out.println("Test 6:");
        analyzer.containsTest(analyzer.data2, ("hi"));
    }
}
