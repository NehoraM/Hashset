
/**
 * implements the class of a closed hash set.
 * @author nehora.moshe
 */

public class ClosedHashSet extends SimpleHashSet {

    /**
     * the closed hash set array.
     */
    private String[] arr;

    private static final String DELETED= new String();

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){

        super(upperLoadFactor, lowerLoadFactor);

        arr = new String [capacity];

        for (int i=0; i<capacity ; i++){
            arr[i]=null;
        }
    };

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        this (DEFAULT_HIGHER_CAPACITY,DEFAULT_LOWER_CAPACITY);
    };

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data the data to add
     */

    public ClosedHashSet(java.lang.String[] data){
        this ();

        for (String str:data)
        {
            this.add(str);
        }
    };

    /**
     *
     * @return the capacity
     */
    @Override
    public int capacity() {
        return this.arr.length;
    }

    /**
     *
     * @param newValue New value to add to the set
     * @return true if tha added pass successfully. false otherwise.
     */
    @Override
    public boolean add(String newValue) {

        //  checks if we have that value already
        if (contains(newValue)) {
            return false;
        }

        // checks that the load factor is ok
        if((double) (this.numOfElements + 1) / this.capacity > this.upperLoadFactor)
        {
            this.changeHashSetSize(this.arr, 1);
        }

        // add value to array
        this.addStringToArray(newValue, this.arr);

        this.numOfElements++;

        return true;
    }

    /**
     * check if value contain.
     * @param searchVal Value to search for
     * @return true if this value contain in the close hash set. false otherwise.
     */
    @Override
    public boolean contains(String searchVal) {
        return this.indexValue(searchVal, this.arr) != -1;
    }

    /**
     * delete value
     * @param toDelete Value to delete
     * @return  true if the value deleted. false otherwise
     */
    @Override
    public boolean delete(String toDelete) {

        if(!this.contains(toDelete))
        {
            return false;
        }

        // checks that the load factor is ok
        if((double) (this.numOfElements - 1) / this.capacity < this.lowerLoadFactor)
        {
            this.changeHashSetSize(this.arr, -1);
        }

        int length= arr.length;
        int hash= toDelete.hashCode();
        for (int i = 0; i < this.arr.length; i++) {

            int loc = closedHashCode(hash, i , length);

            //find place to this value.
            if (this.arr[loc] != null && this.arr[loc].equals(toDelete)) {
                this.arr[loc] = DELETED;
                break;
            }
        }

        this.numOfElements --;
        return true;
    }

    /**
     *
     * @return the number of element in the close hash set
     */
    @Override
    public int size() {
        return this.numOfElements;
    }

    /**
     * Find the index in the array of given value
     * @param value the value we look for his index
     * @return the index of the value, -1 if the value not exist.
     */
    private int indexValue(String value , String[] array){

        int hash= value.hashCode();
        for (int i=0; i < array.length; i++){

            int loc = closedHashCode(hash,i);
            //if the searchVal exist
            if (array[loc]==null)
                return -1;
            else if (array[loc] == DELETED)
                continue;
            else if (array[loc].equals(value))
                return loc;
        }

        return -1;
    }

    /**
     * @param length the length of the array
     * @param hash the hashCode of this value
     * @param i  the number of repetitions we looking for hashCode
     * @return the hash code for this string
     */
    private int closedHashCode(int hash,int i, int length){
        return clamp(hash+ (i+i*i)/2 , length);
    }

    /**
     *
     * @param hash the hashCode of this value
     * @param i the number of repetitions we looking for hashCode
     * @return the hash code for this string
     */
    private int closedHashCode(int hash,int i){
        return closedHashCode(hash, i, capacity);
    }

    /**
     *
     * @param newValue  the array to add the value
     * @param array False if newValue already exists in the set, true otherwise (if the added passed successfully).
     */
    private void addStringToArray(String newValue, String[] array) {

        int length= array.length;
        int hash= newValue.hashCode();
        for (int i = 0; i < array.length; i++) {

            int loc = closedHashCode(hash, i ,length );

            //find place to this value.
            if (array[loc] == null || array[loc] == DELETED ) {
                array[loc] = newValue;
                break;
            }
        }
    }

    /**
     * @param array the current closed-hash-set
     * @param i if i==1, the method increase the table. if i==-1, the method decrease the table.
     */
    private void changeHashSetSize(String[] array, int i){

        // checks that the length not to small to shrink
        if (array.length==1) {
            return;
        }

        // checks if we need to enlarge or to shrink the array
        else if (i==1) {
            capacity = 2 * capacity;
        }
        else {
            capacity = capacity / 2;
        }

        String[] newArray= new String[capacity];


        // adds the old value to the new array
        for (int j=0 ; j<array.length ; j++){
            if (array[j] != null  &&  array[j] != DELETED)
                addStringToArray(array[j], newArray);
        }

        this.arr = newArray;
        this.capacity = this.arr.length;

    };
}
