import org.w3c.dom.ls.LSOutput;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * a class to analyze data from the list with
 * @author nehora.moshe
 */

public class SimpleSetPerformanceAnalyzer {

     public static void main(String[] args) {
        askUser();
        timeTest();

    }

    private static boolean[] toTest = new boolean[6];

    private final static String[] NAMES = {"OpenHashSet", "ClosedHashSet", "TreeSet" ,
            "LinkedList", "HashSet"};

    private static final int WARM_UP_LINKED_LIST = 7000;
    private static final int WARM_UP_NOT_LINKED_LIST = 70000;
    private static final String WORD = "hi";
    private static int WARM_UP;
    private static final String NUMBER = "-1317089015";
    private final static String DATA1 = "C:\\ex4_try2\\src\\data1.txt";
    private final static String DATA2 = "C:\\ex4_try2\\src\\data2.txt";
    private final static String[] data1Array = Ex4Utils.file2array(DATA1);
    private final static String[] data2Array = Ex4Utils.file2array(DATA2);
    private static final int TO_MS = 1000000;
    private static final String TWENTY_THREE="23";


    private static void askUser() {

        Scanner userInput = new Scanner(System.in);

        System.out.println("what test do you wanna test?");
        System.out.println("if yes press y");

        System.out.println("add data1");
        if (userInput.next().equals("y"))
            toTest[0] = true;

        System.out.println("add data2");
        if (userInput.next().equals("y"))
            toTest[1] = true;

        System.out.println(" contains(\"hi\") when it's initialized with data1.txt");
        if (userInput.next().equals("y"))
            toTest[2] = true;

        System.out.println("  contains(\"-13170890158\") when it is initialized with the\n" +
                "words in data1.txt");
        if (userInput.next().equals("y"))
            toTest[3] = true;

        System.out.println("  contains(\"23\") when it's initialized with data2.txt");
        if (userInput.next().equals("y"))
            toTest[4] = true;

        System.out.println("contains(\"hi\") when it's initialized with data2.txt");
        if (userInput.next().equals("y"))
            toTest[5] = true;

    }



    /**
     * initializes the set we want to test
     * @return an array with initialized sets we wanna test
     */
    private static  SimpleSet[] createSimpleSets() {

        SimpleSet[] setHolder = new SimpleSet[5];

        setHolder[0] = new OpenHashSet();

        setHolder[1] = new ClosedHashSet();

        setHolder[2] = new CollectionFacadeSet(new TreeSet<>());

        setHolder[3] = new CollectionFacadeSet(new LinkedList<String>());

        setHolder[4] = new CollectionFacadeSet(new HashSet<String>());

        return setHolder;
    }

    /**
     * figures our for each data type how long add will take for all the data from a file
     * @param sets the SimpleSet array to check
     * @param toAdd the strings to add
     * print how long did it take
     */
    private static void addCheck ( SimpleSet [] sets, String [] toAdd , String dataName ){

        for (int i=0  ; i<sets.length ; i++) {

            long timeBefore = System.nanoTime();
            add(sets[i],toAdd,dataName);
            long timeAfter = System.nanoTime();
            long time = (timeAfter - timeBefore)/TO_MS;
            System.out.println("takes " + time + " ms to add " + dataName + " to " + NAMES[i] );

        }
        System.out.println("\n");
    }


    /**
     *
     * @param sets the SimpleSet array to check
     * @param dataName the dataFile to check
     * @param word the word to check
     */
    private static void containsCheck (SimpleSet [] sets, String dataName , String word){

        for (int i=0  ; i<sets.length ; i++) {

            if (NAMES[i].equals("LinkedList"))
                WARM_UP=WARM_UP_LINKED_LIST;
            else
                WARM_UP=WARM_UP_NOT_LINKED_LIST;


            //warm up
            for(int k=0 ; k<WARM_UP ; k++){
                sets[i].contains(word);
            }

            //check time
            long timeBefore = System.nanoTime();

            for(int k=0 ; k<WARM_UP ; k++){
                sets[i].contains(word);
            }

            long timeAfter = System.nanoTime();

            long time = (timeAfter - timeBefore)/(WARM_UP);

            System.out.println("takes " + time + " ns to check contains "+ word + " to " + NAMES[i] );
        }

        System.out.println("\n");

    }


    /**
     * add string to set
     * @param set the set to add to
     * @param toAdd the set to add
     * @param dataName the name of the data that added
     */
    private static void add( SimpleSet set, String [] toAdd , String dataName ){
        for (int j=0 ; j<toAdd.length ; j++){
            set.add(toAdd[j]);
        }

    }

    /**
     * the full test. test each test only if the user want to.
     */
    private static void timeTest(){
        SimpleSet [] sets1= createSimpleSets();
        SimpleSet [] sets2= createSimpleSets();
        SimpleSet [] sets3= createSimpleSets();
        SimpleSet [] sets4= createSimpleSets();
        SimpleSet [] sets5= createSimpleSets();
        SimpleSet [] sets6= createSimpleSets();

        if (toTest[0]==true) {
            addCheck(sets1, data1Array, "data1");
        }

        if (toTest[1]==true) {
            addCheck(sets2, data2Array, "data2");
        }

        if (toTest[2]==true){
            for (int i=0 ; i<sets3.length ; i++)
                add(sets3[i] , data1Array , "data1");
            containsCheck(sets3 , WORD , WORD);
        }

        if (toTest[3]==true){
            for (int i=0 ; i<sets4.length ; i++)
                add(sets4[i] , data1Array , "data1");
             containsCheck(sets4 , NUMBER , NUMBER);
        }

        if (toTest[4]==true){
            for (int i=0 ; i<sets5.length ; i++)
                add(sets5[i] , data2Array , "data2");
            containsCheck(sets5 , TWENTY_THREE , TWENTY_THREE);
        }

        if (toTest[5]==true){
            for (int i=0 ; i<sets6.length ; i++)
                add(sets6[i] , data2Array , "data2");
            containsCheck(sets6 , WORD , WORD);
        }
    }

}
