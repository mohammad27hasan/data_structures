/*
* Data structure: Hash table
* Abstract data type: Linked hash table
* Version: 1.0
* Author: Mohammad Hasan
*/
package ds.hash;

public class LinkedHashTable<K, V> {
    private class Entry {
        Entry prev;
        K key;
        V value;
        Entry next;

        Entry(K key, V value) {
            prev = null;
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private java.util.ArrayList<Entry> list;
    private int capacity;
    private float loadFactor;
    private int size;

    public LinkedHashTable(int initialCapacity, float loadFactor) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        if ((loadFactor < 0.01F) || Float.isNaN(loadFactor) || Float.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);
        }
        list = new java.util.ArrayList<Entry>(java.util.Collections.nCopies(initialCapacity, null));
        capacity = initialCapacity;
        this.loadFactor = loadFactor;
        size = 0;
    }

    public LinkedHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public LinkedHashTable(int initialCapacity) {
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

    private int hash(K key) {
        return ((key.hashCode() & 0x7FFFFFFF) % capacity);
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
        var newList = new java.util.ArrayList<Entry>(java.util.Collections.nCopies(capacity, null));
        Entry oldEntry = null;
        int index = -1;
        Entry entry = null;
        for (int i = 0; i < OLD_CAPACITY; i++) {
            oldEntry = list.get(i);
            while (oldEntry != null) {
                index = hash(oldEntry.key);
                entry = newList.get(index);
                if (entry == null) {
                    newList.set(index, new Entry(oldEntry.key, oldEntry.value));
                } else {
                    while (entry.next != null) {
                        entry = entry.next;
                    }
                    entry.next = new Entry(oldEntry.key, oldEntry.value);
                    entry.next.prev = entry;
                }
                oldEntry = oldEntry.next;
            }
        }
        list = newList;
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
        int index = hash(key);
        Entry entry = list.get(index);
        if (entry == null) {
            list.set(index, new Entry(key, value));
        } else {
            while (entry.next != null) {
                entry = entry.next;
            }
            entry.next = new Entry(key, value);
            entry.next.prev = entry;
        }
        size++;
    }

    private Entry search(K key) {
        if (isEmpty() || (key == null)) {
            return null;
        }
        Entry entry = list.get(hash(key));
        while (entry != null) {
            if (key.equals(entry.key)) {
                break;
            }
            entry = entry.next;
        }
        return entry;
    }

    public boolean contains(K key) {
        return (search(key) != null);
    }

    public void remove(K key) {
        Entry entry = search(key);
        if (entry == null) {
            throw new IllegalStateException("No Key: " + key);
        }
        if ((entry.prev != null) && (entry.next != null)) {
            entry.prev.next = entry.next;
            entry.next.prev = entry.prev;
        } else if ((entry.prev == null) && (entry.next == null)) {
            list.set(hash(key), null);
        } else if (entry.prev == null) {
            entry = entry.next;
            entry.prev = null;
            list.set(hash(key), entry);
        } else {
            entry = entry.prev;
            entry.next = null;
        }
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
            list.set(i, null);
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
            entry = list.get(i);
            while (entry != null) {
                sb.append(entry.key + "->" + entry.value);
                if (commaSize > 0) {
                    sb.append(", ");
                    commaSize--;
                }
                entry = entry.next;
            }
        }
        sb.append("}");
        return sb.toString();
    }
}