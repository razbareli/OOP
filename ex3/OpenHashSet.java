
/**
 * a hash-set based on chaining
 */
public class OpenHashSet extends SimpleHashSet{


    /**
     * the hash table with the data
     * an array of StringLinkedList objects
     */
    private StringLinkedList[] hashTable;

    /**
     * A default constructor. Constructs a new, empty table
     * with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        hashTableInit();
    }

    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor wanted upper load factor
     * @param lowerLoadFactor wanted lower load factor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        hashTableInit();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * @param data the array of strings to add
     */
    public OpenHashSet(String[] data){
        this();
        for (String curr : data){
            add(curr);
        }
    }

    @Override
    public boolean add(String newValue) {
        if (newValue == null){
            return false;
        }
        if (contains(newValue)){
            return false;
        }
        if ((size+1) / (float)capacity > upperLoadFactor){
            rehashTable(capacity * REHASH_FACTOR);
        }
        int ind = hashFunc(newValue);
        hashTable[ind].getData().add(newValue);
        size++;
        return true;
    }

    @Override
    public boolean contains(String searchVal) {
        if (searchVal == null){
            return false;
        }
        int ind = hashFunc(searchVal);
        return hashTable[ind].getData().contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if (toDelete == null){
            return false;
        }
        int ind = hashFunc(toDelete);
        boolean result = hashTable[ind].getData().remove(toDelete);
        if (result && capacity > MIN_CAP){ // if the item was deleted, check if rehash is needed
            size--;
            if (size/(float)capacity < lowerLoadFactor){
                rehashTable(capacity / REHASH_FACTOR);
            }
        }
        return result;
    }

    /**
     * initializes the hash table with instances of
     * StringLinkedList objects
     */
    private void hashTableInit(){
        hashTable = new StringLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new StringLinkedList();
        }
    }

    /**
     * hash function for strings
     * @param str the string to apply the hash function on
     * @return the index to store the string in the hash table
     */
    private int hashFunc(String str){
        return clamp(str.hashCode());
    }

    /**
     * re-hashes the table
     * @param newSize the new size of hash table
     */
    private void rehashTable(int newSize){
        StringLinkedList[] oldTable = hashTable;
        hashTable = new StringLinkedList[newSize];
        int oldCapacity = capacity;
        capacity = newSize;
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new StringLinkedList();
        }
        for (int i = 0; i < oldCapacity; i++) {
            for (String curr : oldTable[i].getData()) {
                int ind = hashFunc(curr);
                hashTable[ind].getData().add(curr);
            }
        }
    }
}
