import java.util.LinkedList;

/**
 * helps to implement the open hashing problems
 * * @author nehora.moshe
 */

public class OpenHashSetList {

    LinkedList<String> myList;

    /**
     * constructor object with linked list.
     */
    public OpenHashSetList(){
        this.myList= new LinkedList<String>();
    }

    /**
     * check if value contains in the object
     * @param value the value to check
     * @return true if the value contain. false otherwise.
     */
    private boolean contains(String value){
        return this.myList.contains(value);
    }

    /**
     *
     * @return the size if the list
     */
    private int listSize(){
        return this.myList.size();
    }

    /**
     * add value to the list
     * @param value the value to add
     */
    public void addToList(String value){
        if (!contains(value))
            this.myList.add(value);
    }

    /**
     * remove value from the list
     * @param value the value to remove
     */
    private void removeFromList(String value){
        if (contains(value)){
            this.myList.remove(value);
        }
    }
}

