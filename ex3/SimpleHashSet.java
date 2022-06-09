/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet{

    /**
     * Describes the higher load factor of a newly created hash set.
     */
    protected static float DEFAULT_HIGHER_CAPACITY = (float) 0.75;

    /**
     * Describes the lower load factor of a newly created hash set.
     */
    protected static float DEFAULT_LOWER_CAPACITY = (float) 0.25;

    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static int INITIAL_CAPACITY = 16;

    /**
     * current number of items in the hash table
     */
    protected int size = 0;

    /**
     * the current capacity of the hash table
     */
    protected int capacity;

    /**
     * upper load factor of the table
     */
    protected float upperLoadFactor;

    /**
     * lower load factor of the table
     */
    protected float lowerLoadFactor;

    /**
     * rehash factor size
     */
    protected static final int REHASH_FACTOR = 2;

    /**
     * minimum value for the capacity
     */
    protected static final int MIN_CAP = 1;

    /**
     * Constructs a new hash set with the default capacities given
     * in DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet(){
        upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        capacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY,
     * and load factors that the user is choosing.
     * @param upperLoadFactor wanted upper load factor
     * @param lowerLoadFactor wanted lower load factor
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        capacity = INITIAL_CAPACITY;
    }

    /**
     * Returns the size of the storage space currently allocated for the set.
     * @return The current capacity (number of cells) of the table.
     */
    protected int capacity(){
        return capacity;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index The index before clamping.
     * @return An index properly clamped.
     */
    protected int clamp (int index) {
        return index & (capacity-1);
    }

    /**
     * Getter for lower load factor of the table.
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return lowerLoadFactor;
    }

    /**
     * Getter for upper load factor of the table.
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return upperLoadFactor;
    }


    @Override
    public abstract boolean add(String newValue);

    @Override
    public abstract boolean contains(String searchVal);

    @Override
    public abstract boolean delete(String toDelete);

    @Override
    public int size() {
        return size;
    }



}
