/**
 * a class representing the simple hash set
 * @author nehora.moshe
 */

public abstract class SimpleHashSet implements SimpleSet {

    /**
     * Describes the higer load factor of a newly created hash set
     */
    protected static float DEFAULT_HIGHER_CAPACITY=0.75f;

    /**
     * Describes the lower load factor of a newly created hash set
     */
    protected static float DEFAULT_LOWER_CAPACITY=0.25f;

    /**
     * Describes the capacity of a newly created hash set
     */
    protected static int INITIAL_CAPACITY=16;

    /**
     * the higher load factor of the table.
     */
    protected float upperLoadFactor;

    /**
     * The lower load factor of the table.
     */
    protected float lowerLoadFactor;


    /**
     * the current number of the elements in the table
     */
    protected int numOfElements;

    /**
     * the number of cells the table has
     */
    protected int capacity;

    protected static final int ZERO=0;

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor the upper load factor before rehashing
     * @param lowerLoadFactor the lower load factor before rehashing
     */

    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){

        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.numOfElements=ZERO;
    };

    /**
     * Constructs a new hash set with the default capacities given in
     * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
     */
    protected SimpleHashSet(){
        this(DEFAULT_HIGHER_CAPACITY, DEFAULT_LOWER_CAPACITY);
    };


    /**
     * method that return the current capacity (number of cells) of the table.
     * @return The current capacity (number of cells) of the table.
     */

    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table capacity
     * (see the exercise description for details)
     * @param index the index before clamping
     * @return an index properly clamped
     */

   // protected int clamp(int index, int arrayLength){
       // return (index & (arrayLength-1));
    //}


    protected int clamp(int index){

        return (index & (capacity-1));
    };

    protected int clamp(int index , int length){

        return (index & (length -1));
    };



    /**
     * method that return the lower load factor of the table.
     * @return The lower load factor of the table.
     */

    protected float getLowerLoadFactor(){
        return lowerLoadFactor;
    };

    /**
     * method that return the higher load factor of the table.
     * @return The higher load factor of the table.
     */

    protected float getUpperLoadFactor(){
        return upperLoadFactor;
    }

}
