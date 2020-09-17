/**
 *a facade class that will implement all the hard work for us
 * @author nehora.moshe
 */


public class CollectionFacadeSet implements SimpleSet {

    java.util.Collection<String> myCollection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<String> collection){
        this.myCollection = collection;
    };

    /**
     * Description copied from interface: supplied.SimpleSet
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue){

        if (this.contains(newValue)){
            return false;
        }
        return myCollection.add(newValue);
    };

    /**
     * Description copied from interface: supplied.SimpleSet
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set. false otherwise.
     */
    public boolean contains(String searchVal){

        return myCollection.contains(searchVal);
    };

    /**
     * Description copied from interface: supplied.SimpleSet
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted. false otherwise.
     */
    public boolean delete(String toDelete){

        return myCollection.remove(toDelete);
    }

    /**
     * return The number of elements currently in the set.
     * @return The number of elements currently in the set
     */
    public int size(){
        return myCollection.size();
    }
}
