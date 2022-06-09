import java.util.LinkedList;

/**
 * Wrapper class that has a LinkedList<String>
 * and delegates methods to it
 */
public class StringLinkedList {
    /**
     * the linked list containing the data
     */
    private LinkedList<String> data = new LinkedList<String>();

    /**
     * getter to the linked list with the data
     * @return the linked list with the data
     */
    public LinkedList<String> getData(){
        return data;
    }
}
