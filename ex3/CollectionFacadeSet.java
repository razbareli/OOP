import java.util.Collection;
import java.util.HashSet;

/**
 * Wraps an underlying Collection<String>and serves to both simplify its API and give it a common
 * type with the implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {

    /**
     * The warped object by this facade.
     */
    protected Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection
     * @param collection the collection provided
     */
    CollectionFacadeSet(Collection<String> collection) {
        HashSet<String> temp = new HashSet<String>(collection);
        collection.clear();
        collection.addAll(temp);
        this.collection = collection;
    }

    @Override
    public boolean add(String newValue) {
        if (!collection.contains(newValue)) {
            return collection.add(newValue);
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        return collection.remove(toDelete);
    }

    @Override
    public int size() {
        return collection.size();
    }
}
