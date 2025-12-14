/*
* Data structure: Hash table
* Abstract data type: Array hash table
* Version: 1.0
* Author: Mohammad Hasan
*/
package ds.hash;

public class ArrayHashTable<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private java.util.ArrayList<Entry> array;
    private int capacity;
    private int size;
    private final Entry DELETED;

    public ArrayHashTable(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        array = new java.util.ArrayList<Entry>(java.util.Collections.nCopies(capacity, null));
        this.capacity = capacity;
        size = 0;
        DELETED = new Entry(null, null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == capacity);
    }

    private int auxiliaryHash(K key) {
        return ((key.hashCode() & 0x7FFFFFFF) % capacity);
    }

    private int hash(K key, int probeNumber) {
        return ((auxiliaryHash(key) + probeNumber) % capacity);
    }

    public void insert(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key: " + key);
        }
        if (value == null) {
            throw new NullPointerException("Value: " + value);
        }
        if (isFull()) {
            throw new IllegalStateException("Size: " + (size + 1) + ", Capacity: " + capacity);
        }
        if (contains(key)) {
            throw new IllegalStateException("Duplicate Key: " + key);
        }
        int index = -1;
        Entry entry = null;
        for (int i = 0; i < capacity; i++) {
            index = hash(key, i);
            entry = array.get(index);
            if ((entry == null) || (entry == DELETED)) {
                array.set(index, new Entry(key, value));
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
            entry = array.get(index);
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
            entry = array.get(index);
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
        array.set(index, DELETED);
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
            array.set(i, null);
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
            entry = array.get(i);
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