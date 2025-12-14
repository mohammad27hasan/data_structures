/*
* Data structure: Hash table
* Abstract data type: Dynamic hash table
* Version: 1.0
* Author: Mohammad Hasan
*/
package ds.hash;

public class DynamicHashTable<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private java.util.ArrayList<Entry> vector;
    private int capacity;
    private float loadFactor;
    private int size;
    private final Entry DELETED;

    public DynamicHashTable(int initialCapacity, float loadFactor) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        if ((loadFactor < 0.01F) || Float.isNaN(loadFactor) || Float.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);
        }
        vector = new java.util.ArrayList<Entry>(java.util.Collections.nCopies(initialCapacity, null));
        capacity = initialCapacity;
        this.loadFactor = loadFactor;
        size = 0;
        DELETED = new Entry(null, null);
    }

    public DynamicHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public DynamicHashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private boolean isPartiallyFull() {
        return (((float)(size) / (float)(capacity)) >= loadFactor);
    }

    private int auxiliaryHash(K key) {
        return ((key.hashCode() & 0x7FFFFFFF) % capacity);
    }

    private int hash(K key, int probeNumber) {
        return ((auxiliaryHash(key) + probeNumber) % capacity);
    }

    private boolean isPrime(int number) {
        int squareRoot = (int)(Math.sqrt(number));
        for (int i = 2; i <= squareRoot; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int startNumber) {
        int currentNumber = startNumber + 1;
        while (true) {
            if (isPrime(currentNumber)) {
                return currentNumber;
            }
            currentNumber++;
        }
    }

    private void rehash() {
        final int OLD_CAPACITY = capacity;
        capacity = nextPrime(OLD_CAPACITY * 2);
        var newVector = new java.util.ArrayList<Entry>(java.util.Collections.nCopies(capacity, null));
        Entry entry = null;
        int j = -1;
        int index = -1;
        for (int i = 0; i < OLD_CAPACITY; i++) {
            entry = vector.get(i);
            if ((entry == null) || (entry == DELETED)) {
                continue;
            }
            for (j = 0; j < capacity; j++) {
                index = hash(entry.key, j);
                if (newVector.get(index) == null) {
                    newVector.set(index, entry);
                    break;
                }
            }
        }
        vector = newVector;
    }

    public void insert(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key: " + key);
        }
        if (value == null) {
            throw new NullPointerException("Value: " + value);
        }
        if (contains(key)) {
            throw new IllegalStateException("Duplicate Key: " + key);
        }
        if (isPartiallyFull()) {
            rehash();
        }
        int index = -1;
        Entry entry = null;
        for (int i = 0; i < capacity; i++) {
            index = hash(key, i);
            entry = vector.get(index);
            if ((entry == null) || (entry == DELETED)) {
                vector.set(index, new Entry(key, value));
                break;
            }
        }
        size++;
    }

    private Entry search(K key) {
        if (isEmpty() || (key == null)) {
            return null;
        }
        int index = -1;
        Entry entry = null;
        for (int i = 0; i < capacity; i++) {
            index = hash(key, i);
            entry = vector.get(index);
            if (entry == null) {
                break;
            }
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    public boolean contains(K key) {
        return (search(key) != null);
    }

    private int find(K key) {
        if (isEmpty() || (key == null)) {
            return -1;
        }
        int index = -1;
        Entry entry = null;
        for (int i = 0; i < capacity; i++) {
            index = hash(key, i);
            entry = vector.get(index);
            if (entry == null) {
                break;
            }
            if (key.equals(entry.key)) {
                return index;
            }
        }
        return -1;
    }

    public void remove(K key) {
        int index = find(key);
        if (index == -1) {
            throw new IllegalStateException("No Key: " + key);
        }
        vector.set(index, DELETED);
        size--;
    }

    public V get(K key) {
        Entry entry = search(key);
        if (entry == null) {
            throw new IllegalStateException("No Key: " + key);
        }
        return entry.value;
    }

    public void set(K key, V value) {
        Entry entry = search(key);
        if (entry == null) {
            throw new IllegalStateException("No Key: " + key);
        }
        entry.value = value;        
    }

    public void clear() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < capacity; i++) {
            vector.set(i, null);
        }
        size = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Entry entry = null;
        int commaSize = size - 1;
        for (int i = 0; i < capacity; i++) {
            entry = vector.get(i);
            if ((entry == null) || (entry == DELETED)) {
                continue;
            }
            sb.append(entry.key + "->" + entry.value);
            if (commaSize > 0) {
                sb.append(", ");
                commaSize--;
            }
        }
        sb.append("}");
        return sb.toString();
    }
}