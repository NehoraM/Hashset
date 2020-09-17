import java.util.LinkedList;

/**
 *  a class representing an open hash set
 * @author nehora.moshe
 */
public class OpenHashSet extends SimpleHashSet {

    private OpenHashSetList[] openHashSetArray;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        openHashSetArray = new OpenHashSetList[INITIAL_CAPACITY];

        for (int i=0 ; i<this.capacity() ; i++)
            openHashSetArray[i]=new OpenHashSetList();
    }


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        this(DEFAULT_HIGHER_CAPACITY, DEFAULT_LOWER_CAPACITY);
    }

    /**
     * @param value the value to hash
     * @return the hash code to this value
     */
    private int openHashCode(String value) {
        return clamp(value.hashCode());
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        this();

        for (int i = 0; i < data.length; i++){
            if (data[i]!=null)
            add(data[i]);
        }
    }


    /**
     * @param array the current open-hash-set
     * @param i     if i==1, the method increase the table. if i==-1, the method decrease the table.
     */
    private void changeHashSetSize(OpenHashSetList[] array, int i) {

        if (array.length == 1)
        {
            return;
        }

        if (i == 1)
            capacity = capacity * 2;
        else
            capacity = capacity / 2;

        OpenHashSetList[] newArray = new OpenHashSetList[capacity];

        for(int k=0; k < newArray.length; k++) {
            newArray[k] = new OpenHashSetList();
        }

        for (OpenHashSetList openHashSetList : openHashSetArray) {
            for (String str : openHashSetList.myList) {
                newArray[this.clamp(str.hashCode())].addToList(str);
            }
        }

        openHashSetArray = newArray;
    }


    /**
     * Description copied from interface: supplied.SimpleSet
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set, true otherwise.
     */
    public boolean add(java.lang.String newValue) {

        int loc = indexValue(newValue);

        if (loc == -1) {

            if ((numOfElements + 1) / (double)capacity > upperLoadFactor) {
                changeHashSetSize(openHashSetArray, 1);
            }

            openHashSetArray[this.clamp(newValue.hashCode())].myList.add(newValue);
            numOfElements++;
            return true;
        }
        return false;
    }


    /**
     * Description copied from interface: supplied.SimpleSet
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set, false otherwise.
     */

    public boolean contains(java.lang.String searchVal) {
        return (indexValue(searchVal) != -1);
    }


    /**
     * Find the index in the array of given value
     * @param value the value we look for his index
     * @return the index of the value, -1 if the value not exist.
     */
    private int indexValue(String value) {
        int loc = openHashCode(value);
            //if the searchVal exist
            if (this.openHashSetArray[loc] == null||!this.openHashSetArray[loc].myList.contains(value))
                return -1;
            else
                return loc;

    }

    /**
     * Description copied from interface: supplied.SimpleSet
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete.
     * @return True iff toDelete is found and deleted, false otherwise.
     */
    public boolean delete(java.lang.String toDelete) {
        int loc = indexValue(toDelete);

        if (loc != -1) {

            if ((numOfElements - 1) / (double)capacity < lowerLoadFactor && capacity !=1)
                changeHashSetSize(openHashSetArray, -1);

            openHashSetArray[this.clamp(toDelete.hashCode())].myList.remove(toDelete);
            numOfElements--;
            return true;
        }

        return false;
    }

    /**
     * method that return the number of elements currently in the set.
     *
     * @return The number of elements currently in the set.
     */
    public int size() {
        return numOfElements;
    }

    /**
     * method that return the current capacity (number of cells) of the table.
     *
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return capacity;
    }
}



