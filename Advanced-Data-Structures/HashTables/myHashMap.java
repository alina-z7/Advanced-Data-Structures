
/****************************************
 *
 *  This sample code is illustrating a hash table using separate chaining. To illustrate this,
 *  the  code is building a Hash Map implementation that emulates Java's HashMap class which implements
 *  the Map interface.
 *
 *  This is a simple sample class demonstrating the use of separate chaining. It is emulating the
 *  interfaces / behavior of Java's HashMap using the Map interface. It is not intended to be a
 *  full-blown Hash Map / Hash Table implementation, nor does it implement all methods in Java's
 *  HashMap class. But the ones that are implemented emulate how those methods work in HashMap
 *  (exception, the method printAllElements() below, which was added for quick testing, as the
 *  method entrySet() is not implemented.
 *
 *  CAVEAT: as indicated, Java provides a HashMap class that is implemented on the Map Interface that
 *  is more robust, and expansive than this implementation. But what is implemented operates similarly.
 *  This coding example is for sample coding of how hash tables using separate chaining (versus
 *  open addressing approaches) would work using the Map interface behavior.
 *
 *  PUBLIC METHODS:
 *  ---------------
 *
 *     void  clear()             - Removes all of the mappings from this map.
 *  boolean  containsValue(V)    - Returns true if this map maps one or more keys to the specified value
 *  boolean  containsKey(K)      - Returns true if this map contains a mapping for the specified key.
 *       V   get(K)              - Returns the value to which the specified key is mapped, or null
 *                                 if this map contains no mapping for the key
 *       V   put(K, V)           - Associates the specified value with the specified key in this map
 *       V   putIfAbsent(K, V)   - If the specified key is not already associated with a value (or
 *                                 is mapped to null) associates it with the given value and returns
 *                                 null, else returns the current value
 *       V   remove(K)           - Removes the entry for the specified key only if it is currently
 *                                 mapped to the specified value
 *  boolean  remove(K, V)        - Removes the entry for the specified key only if it is currently
 *                                 mapped to the specified value.
 *  boolean  replace(K, V)       - Replaces the entry for the specified key only if it is currently
 *                                 mapped to some value
 *        V  replace(K, V1, V2)  - Replaces the entry for the specified key only if currently mapped
 *                                 to the specified value.
 *      int  size()              - returns the number of <k,v> pairs in hashmap
 *
 *      void  printAllElements() - for debugging, prints hash map to screen (EntrySet is not implemented).
 *
 *
 *  Methods *NOT* implemented to fully emulate the behavior of Java's HashMap Class
 *      - clone()
 *      - compute()
 *      - computeIfAbsent()
 *      - computeIfPresent()
 *      - entrySet()
 *      - foreach()
 *      - keySet()
 *      - merge(()
 *      - putAll()
 *      - replaceAll()
 *      - values()
 *
 ****************************************/

import java.util.*;

/*
 * Node representing a <Key, Value> pair stored in the Hash Map, elements hashed to the
 * same bucket slot will be chained through a singly linked-list.
 */

class HashNode<K,V> {
    K key;
    V value;
    HashNode<K, V> next;
    public HashNode() {
        this.key=key;
        this.value=value;
    }
}


/*
 * A simple implementation of a HashMap that is build to emulate the Map Interface. The <key, values>
 * pairs are stored in a Map, where the key represents a hash bucket slot number and the value represents
 * a node which will form as linked-list for hash collisions on that bucket's slot.
 *
 * The array in this class represents the buckets, and each bucket has a pointer to a node class
 * for the linked-list of <k,v> pairs. The key for this bucket array is generated using a hash
 * function that returns a number from 0 to n-1, where n is the number of buckets (array size).
 *
 * Note: Java provides a HashMap class which implements the HashMap on the Map interface. Again, the
 * intent is not to replace it and/or build out to the same level. We are illustrating 'separate chaining'
 * using a singly linked-list.
 */

class myHashMap<K,V> {

    private static final float DEFAULT_LOAD_FACTOR = 0.7f;
    private static final int   INITIAL_NUM_BUCKETS = 10;

    ArrayList<HashNode<K, V>> bucket = new ArrayList<>();
    int numBuckets = INITIAL_NUM_BUCKETS;
    int size = 0;

    public myHashMap() {
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    public int Size()           { return size; }
    public boolean isEmpty()    { return size == 0; }


    /*
     * Method clear()
     *
     * Reinitialize the hash to INITIAL_NUM_BUCKETS. For each bucket, it resets the
     * bucket slots (in the array) to a null Node.
     */

    public void clear() {
        size = 0;
        numBuckets = INITIAL_NUM_BUCKETS;
        bucket = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }


    /*
     * method getBucketindex()
     *
     * Performs two parts.
     *   1) First invokes a very simple hash code generator which generates a 32-bit integer. The mask
     *      (bit operation) masks off the sign bit )turns the 32-bit integer into a 31-bit non-negative
     *      integer).
     *   2) Second, it invokes a compressor expression (in this case, performing a MOD operation).
     *      This compresses the hash number to between 0 and (numBuckets-1) which will be an index
     *      into our hash bucket slot (aka, key for Map);
     *
     *  @param key - key value to locate hash map bucket for
     *
     *  @return bucketIndex - bucket index number for key value
     */

    private int getBucketIndex(K key) {
        return  (key.hashCode() & 0x7fffffff) % numBuckets;
    }


    /*
     * method: V get(K)
     *
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping
     * for the key. This method will probe to the correct bucket, then loop through the bucket's chained
     * nodes (linked-list) until the key is found. If not found, the key is not in the hash map and
     * the method will return null.
     *
     * @param key - key value for identifying the <k,v> pair
     *
     * @return val - value for the provided key value, else null
     */

    public V get(K key) {
        int index = getBucketIndex(key);

        HashNode<K, V> head = bucket.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }


    /*
     * method: V remove(K)
     *
     * Removes the entry for the specified key only if it is currently mapped to the specified
     * value. The method will probe into the bucket lists. If the bucket has a chained list, then
     * it will be traversed to identify the key to remove. If no chained list and/or no node is
     * found the chained list representing the key, then it is not in the hash map.
     *
     * If the key is found, it is removed from the chained list and the hashmap size is adjusted.
     *
     * @param key - key value for the <key,value> pair to remove
     *
     * @return value - return the node for the <key,value> removed, else null if not found
     */

    public V remove(K key) {
        int index = getBucketIndex(key);

        HashNode<K, V> head = bucket.get(index);
        if (head == null) {
            // no elements at the hashed bucket location,
            // key is not in the hashmap
            return null;
        }
        if (head.key.equals(key)) {

            // key found at the head of the bucket's location.
            V val = head.value;
            head = head.next;
            bucket.set(index, head);
            size--;
            return val;
        } else {

            // Key is not at the bucket's head location,
            // we need to traverse the linked-list to locate it
            HashNode<K, V> prev = null;
            while (head != null) {

                if (head.key.equals(key)) {
                    prev.next = head.next;
                    size--;
                    return head.value;
                }
                prev = head;
                head = head.next;
            }

            // element not found in linked-list,
            // key not in the hash map
            return null;
        }
    }


    /*
     * Method: boolean replace(K, V)
     *
     * Replaces the entry for the specified key only if it is currently mapped to some value
     *
     * @param: key - key for identifying <k,v>
     * @param: val - will remove <k,v> only if existing value equals val
     *
     * @return: true if deleted, else false
     */

    public boolean remove(K key, V val) {

        V originalValue = get(key);

        if (originalValue == null || (! originalValue.equals(val)) ) {
            return false;
        }

        // Key was found and its value equals teh passed parameter 'val'
        remove(key);

        return true;
    }


    /*
     * method: V put(K, V)
     *
     * Associates the specified value with the specified key in this map. The method will
     * check if the key is already in the hash map, if so, it updates the value and
     * returns the old value. If the key is not found, then it will insert the <k,v> pair.
     * Last if inserting the <k,v>, the load factor is checked. If it is greater than the
     * DEFAULT_LOAD_FACTOR %, the method will double the bucket map and rehash the whole hash map.
     *
     * @param key   - Key to the <k,v> pair operate on
     * @param value - if key found, value is updated to this param, else routine inserts <k,v>
     *
     * @return value - if key exists, returns old value before replacing with provided value, else null.
     */

    public V put(K key, V value) {

        /*
         * If the <key,value> already exists in the hash map,
         * then replace teh value, else insert the <key,value>
         */
        V oldValue = get(key);
        if ( oldValue != null) {
            replace(key, value);
            return oldValue;
        }

        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        HashNode<K, V> toAdd = new HashNode<>();
        toAdd.key = key;
        toAdd.value = value;
        if (head == null) {
            bucket.set(index, toAdd);
            size++;

        } else {
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    size++;
                    break;
                }
                head = head.next;
            }
            if (head == null) {
                head = bucket.get(index);
                toAdd.next = head;
                bucket.set(index, toAdd);
                size++;
            }
        }

        /*
         * Check the load factor of the hashmap, if greater than DEFAULT_LOAD_FACTOR,
         * we will double the number of buckets of our hashmap.
         */

        if ((1.0 * size) / numBuckets > DEFAULT_LOAD_FACTOR) {
            //do something
            ArrayList<HashNode<K, V>> tmp = bucket;
            bucket = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;

            for (int i = 0; i < numBuckets; i++) {
                bucket.add(null);
            }

            /*
             * Traverse the original buckets, and for each bucket
             * traverse the nodes stored there (via linked-list).
             * For each node (<key, value> pair), add to the new
             * (grown) bucket list. The re-add process will rehash the
             * keys to the new bucket size.
             */
            for (HashNode<K, V> headNode : tmp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }

        return null;
    }


    /*
     * method: V putIfAbsent(K, V)
     *
     * If the specified key is not already associated with a value (or is mapped to null) associates
     * it with the given value and returns null, else returns the current value.
     *
     * @param: key   - The key to check if exists in the hashmap
     * @parem: value - The value to place in as a <k, v> pair if key does not exist
     *
     * @return: V - returns the existing value if the key is found, else null
     */

    public V putIfAbsent(K key, V value) {
        V originalValue = get(key);

        if (originalValue == null ) {
            put(key, value);
            return null;
        }

        return originalValue;
    }


    /*
     * method: V replace(K, V)
     *
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     *
     *  @param key   - Key for the <k, v> pair to replace its value
     *  @param val   - The new value to replace the old one if found.
     *
     *  @return V  - returns the old value for the <k,v> pair, else null if not found.
     */

    public V replace(K key, V val) {
        int index = getBucketIndex(key);
        V oldVal;

        HashNode<K, V> head = bucket.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                oldVal = head.value;
                head.value = val;
                return oldVal;
            }
            head = head.next;
        }
        // key not found
        return null;
    }


    /*
     * method: boolean replace(K, V, V)
     *
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     *
     *  @param key    - Key for the <k, v> pair to replace its value
     *  @param oldVal - Replace only if current <k,v>'s value is same as oldVal
     *  @param newVal - the new value to use.
     *
     *  @return V  - returns the old value for the <k,v> pair, else null if not found.
     */

    public boolean replace(K key, V oldVal, V newVal) {
        if (oldVal == newVal) return true;

        int idx = getBucketIndex(key);
        HashNode<K, V> curr = bucket.get(idx);

        while (curr != null) {
            if (curr.key.equals(key) && curr.value.equals(oldVal)) {
                curr.value = newVal;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }


    /*
     * Method: boolean contains(V)
     *
     * Returns true if this map maps one or more keys to the specified value
     *
     * @param val: Value to search for in hashmap to determine if it is contained there.
     *
     * @return: true if found, else false.
     */

    public boolean containsValue(V val) {
        return false;
    }


    /*
     * Method: boolean containsKey(K)
     *
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key: The key to search for to determine of hash map contains it
     *
     * @return: true if found, else false.
     */

    public boolean containsKey (K key)  {
        return false;
    }


    /*
     * method printAllElement()
     *
     * printAllElements() will simply print all elements to the screen's display. This is not
     * a standard routine that you would expect on Java's HashMap implementing the Map interface.
     * (recall we are implementing our hashmap on the Map interface). The correct way would be to
     * expose iteration by defining methods such as entrySet(), keySet(), values() that align to the
     * Map Interface.
     *
     * This method is provided simply to help test your code by providing a simple method
     * to print the hashmap to the terminal screen.
     *
     * The method will print ten "<key, value>" pairs per line. There is no specific order of how
     * they are printed, other than always traversing the bucket slots in the same order for each
     * innovation, and per slot, traversing the chained elements (link-list) in same order. This will
     * guarantee that for teh same <key,value> pairs in the hashmap, they will always be printed in the
     * same order for testing purposes.
     */

    public void printAllElements() {
        int counter = 0;
        System.out.println();
        System.out.println("Printing all <key,value> pairs in the HashMap");

        for (HashNode<K, V> headNode : bucket) {
            while (headNode != null) {

                System.out.print("<" + headNode.key + "," + headNode.value + "> ");
                if (counter++ == 10) {
                    System.out.println();   // print 5 elements per line.
                    counter = 0;
                }
                headNode = headNode.next;
            }
        }
        System.out.println();
    }
}



