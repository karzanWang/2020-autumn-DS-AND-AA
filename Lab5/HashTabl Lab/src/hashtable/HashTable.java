package hashtable;

import java.util.ArrayList;

import java.util.LinkedList;

/**
 * This class implements a simple hash table.
 * Hash table element keys must be Integer or String objects.
 * @author tcolburn
 */
public class HashTable {

	private ArrayList<LinkedList<HashTableElement>> chains;

    private ArrayList<Integer> chainLengths;

    private int index;

    public final static int CAPACITY = 11;

    private int size;
	
    /**
     * Creates a new hash table as an array of linked lists.
     * The lists represent the chains of table elements that hash to the same value.
     * The table's size is given by the constant CAPACITY.
     * An auxiliary array chainLengths is also maintained that stores the
     * sizes of the linked lists.
     */
    public HashTable() {

        chains = new ArrayList<LinkedList<HashTableElement>>(CAPACITY);
        chainLengths = new ArrayList<Integer>(CAPACITY);

        for (int i = 0; i < CAPACITY; i++) {
            chains.add(new LinkedList<HashTableElement>());
            chainLengths.add(0);
        }

        size = 0;
    }

    /**
     * Clears the hash table of all elements.
     */
    public void clear() {
        for (int i = 0; i < CAPACITY; i++) {
            chains.get(i).clear();
            chainLengths.set(i, 0);
        }
        size = 0;
    }

    /**
     * Inserts a hash table element into this hash table.
     * @param element the element to be inserted
     */
    public void insert(HashTableElement element) {
        index = hash(element.getKey());
        LinkedList<HashTableElement> chain = chains.get(index);
        chain.addFirst(element);
        chainLengths.set(index, chainLengths.get(index)+1);
        size++;
    }

    /**
     * Search for an element with a given key in this hash table.
     * @param key the key to be searched for.  Must be an Integer or String.
     * @return the element with this key if it exists, null otherwise.
     */
    public HashTableElement search(Object key) {
        index = hash(key);
        LinkedList<HashTableElement> chain = chains.get(index);

        for (HashTableElement hash : chain) {
            if (hash.getKey().equals(key)){
                return hash;
            }
        }
        return null;

    }

    /**
     * Attempts to remove an element from this hash table. true is returned if
     * the element was removed, false if not (that is, the element was not in
     * the table).
     * @param element the element to be removed
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(HashTableElement element) {
        index = hash(element.getKey());
        LinkedList<HashTableElement> chain = chains.get(index);
        if (!chain.contains(element)){
            return false;
        }else {
            chain.remove(element);
            chainLengths.set(index, chainLengths.get(index)-1);
            size--;
            return true;
        }

    }

    /**
     * Returns the hash value for a given key.
     * Only Integers and Strings are currently supported.
     * @param key the key to be hashed.  Must be an Integer or String.
     * @return the hash value for the key
     */
    private static int hash(Object key) {
        Class keyClass = key.getClass();
        if ( keyClass == Integer.class )
            return integerHash((Integer)key);
        else if ( keyClass == String.class )
            return stringHash((String)key);
        else
            throw new IllegalArgumentException("Hashing not supported for " + keyClass);
    }

    /**
     * Returns the hash value for a given Integer key
     * @param key the Integer key to be hashed
     * @return the hash value for the key
     */
    private static int integerHash(Integer key) {
        int result = 0;
        for(int i = 0;i<key;i=i+2){
            result = (result*101)^(key);
        }

        result %= (CAPACITY);
        if(result < 0){
            result += CAPACITY;
        }
        return result;

    }

    /**
     * Returns the hash value for a given String key
     * @param key the String key to be hashed
     * @return the hash value for the key
     */
    private static int stringHash(String key) {
       int result = 0;
       for(int i = 0;i<key.length();i++){
           result = (result << 4) ^ (result >> 28) ^ key.charAt(i);
       }
       result %= (CAPACITY);
        if(result < 0){
            result += CAPACITY;
        }
        return result;

    }

    /**
     * Computes the average chain length (load factor) for this
     * hash table.
     * @return the load factor (average chain length)
     */
    public double meanChainLength() {
        double sum = 0.0;
        for (int i = 0; i < CAPACITY; i++) {
            sum += chainLengths.get(i);
        }
        return sum/CAPACITY;
    }

    /**
     * Computes the standard deviation from the mean chain length
     * for this hash table.
     * A good hash function minimizes the standard deviation.
     * @return the standard deviation from the mean chain length
     */
    public double standardDeviation() {
        double mean = meanChainLength();
        double sum_of_squares = 0.0;
        for (int i = 0; i < CAPACITY; i++) {
            sum_of_squares += Math.pow(chainLengths.get(i) - mean, 2);
        }
        return Math.sqrt(sum_of_squares/CAPACITY);
    }

    /**
     * Returns the array of chain lengths for display purposes.
     * @return the array of chain lengths
     */
    public ArrayList<Integer> getChainLengths() {
        return chainLengths;
    }

    /**
     * Returns this hash table's size, that is, the number of elements in it.
     * @return the size of the hash table
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the index of the last table slot hashed to.
     * @return the index of the last table slot hashed to.
     */
    public int getIndex() {
        return index;
    }

}
