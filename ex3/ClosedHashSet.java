/**
 * A hash-set based on closed-hashing with quadratic probing.
 * Extends SimpleHashSet.
 */
public class ClosedHashSet extends SimpleHashSet{

    /**
     * indicates if something was ever deleted from this cell;
     * if the value of the cell has this variable,
     * then something was deleted from this cell
     */
    private static final String wasDeleted = new String();

    /**
     * probing constant
     */
    private static final int  PROBE_VAL = 2;

    /**
     * return index when value not found
     */
    private static final int NOT_FOUND = -1;

    /**
     * array containing the data of the table
     */
    private String[] hashTable;

    /**
     * max attempt we've made to find an empty spot
     */
    private int longestProbe = 0;

    /**
     * A default constructor. Constructs a new,
     * empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        hashTable = new String[capacity];
    }

    /**
     *Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor,
                   float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new String[capacity];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * @param data the array of strings to add
     */
    public ClosedHashSet(String[] data){
        this();
        for (String curr : data){
            add(curr);
        }
    }

    @Override
    public boolean add(String newValue) {
        if (contains(newValue)){
            return false;
        }
        if ((size+1) / (float)capacity > upperLoadFactor){
            rehashTable(capacity * REHASH_FACTOR);
        }
        int ind;
        for (int i = 0; i < capacity; i++) {
            ind = hashFunc(newValue, i);
            if (i > longestProbe){ //update longest probe
                longestProbe = i;
            }
            if (isEmpty(ind)){
                hashTable[ind] = newValue;
                size++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        return findIndex(searchVal) != -1;
    }

    @Override
    public boolean delete(String toDelete) {
        int ind = findIndex(toDelete);
        if (ind != NOT_FOUND){
            hashTable[ind] = wasDeleted;
            size--;
            if (capacity > MIN_CAP && size/(float)capacity < lowerLoadFactor){
                rehashTable(capacity / REHASH_FACTOR);
            }
            return true;
        }
        return false;
    }


    /**
     * finds the index of the string in the hash table
     * @param str the string to look for
     * @return the index of the string in the table if it exists, -1 otherwise.
     */
    private int findIndex(String str){
        if (str == null){
            return NOT_FOUND;
        }
        String currCell;
        int currInd;
        for (int i = 0; i <= longestProbe; i++) {
            currInd = hashFunc(str, i);
            currCell = hashTable[currInd];
            if (currCell == null) {
                return NOT_FOUND;
            }
            if (currCell == wasDeleted) {//address comparing
                continue;
            }
            if (currCell.equals(str)) {
                return currInd;
            }
        }
        return NOT_FOUND;
    }

    /**
     * indicates if the cell is empty
     * if it is, we can insert a string
     * @param ind index to check
     * @return if cell is empty
     */
    private boolean isEmpty(int ind){
        return hashTable[ind] == wasDeleted || hashTable[ind] == null;
    }

    /**
     * re-hashes the table
     * @param newSize the new size of hash table
     */
    private void rehashTable(int newSize) {
        longestProbe = 0;
        String[] oldTable = hashTable;
        int oldCapacity = capacity;
        hashTable = new String[newSize];
        capacity = newSize;
        for (String curr : oldTable) {
            if (curr != wasDeleted && curr != null) {
                int index;
                for (int i = 0; i < capacity; i++) {
                    index = hashFunc(curr, i);
                    if (i > longestProbe){ //update longest probe
                        longestProbe = i;
                    }
                    if (isEmpty(index)){
                        hashTable[index] = curr;
                        break;
                    }
                }
            }
        }
    }

    /**
     * hash function for strings
     * @param str the string to apply the hash function on
     * @param probe the probing index
     * @return the index to store the string in the hash table
     */
    private int hashFunc(String str, int probe){
        return clamp((str.hashCode() + (probe + probe*probe)/PROBE_VAL));
    }


}
