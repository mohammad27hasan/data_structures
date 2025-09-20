package ds.heap;

public class MinHeap<T extends Comparable<T>> {   
    private class Item {
        T data;
        Item(T val) {
            data = val;
        }

        boolean isLessThan(Item item) {
            return (this.data.compareTo(item.data) < 0);
        }
    }
    private static final int DEFAULT_CAPACITY = 14;
    private Item[] vector;
    private int capacity;
    private int size;
    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity is less 1");
        }
        vector = createArray(initialCapacity);
        capacity = initialCapacity;
        size = 0;
    }

    public MinHeap(T[] array) {
        capacity = array.length + DEFAULT_CAPACITY;
        vector = createArray(capacity);
        size = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                throw new NullPointerException("The specified array contains null");
            }
            insert(array[i]);
        }
    }

    public MinHeap(MinHeap<T> other) {
        capacity = other.size() + DEFAULT_CAPACITY;
        vector = createArray(capacity);
        size = 0;
        while (!other.isEmpty()) {
            vector[size++] = new Item(other.extractMin());
        }
        for (int i = 0; i < size; i++) {
            other.insert(vector[i].data);
        }
    }

    @SuppressWarnings("unchecked")
    private Item[] createArray(int length) {
        return (Item[])(java.lang.reflect.Array.newInstance(Item.class, length));
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private boolean isFull() {
        return (size == capacity);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Min heap is empty");
        }
        return vector[0].data;
    }

    public T peek() {
        return findMin();
    }

    private int parent(int i) {
        return ((i - 1) / 2);
    }

    private int left(int i) {
        return (2 * i + 1);
    }

    private int right(int i) {
        return (2 * i + 2);
    }

    private void resize() {
        capacity = capacity + DEFAULT_CAPACITY;
        Item[] temp = createArray(capacity);
        for (int i = 0; i < size; i++) {
            temp[i] = vector[i];
        }
        vector = temp;
    }

    private void swap(int i, int j) {
        Item temp = vector[i];
        vector[i] = vector[j];
        vector[j] = temp;
    }

    private void shiftUp(int i) {
        int p = parent(i);
        while (vector[i].isLessThan(vector[p])) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }
    
    public void insert(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        }
        if (isFull()) {
            resize();
        }
        vector[size++] = new Item(item);
        shiftUp(size - 1);
    }

    public void push(T item) {
        insert(item);
    }

    private void shiftDown(int p) {
        int j = 0;
        int r = 0;
        int l = 0;
        do {
            j = -1;
            r = right(p);

            //Check if right child exists and is less than its parent
            if ((r < size) && vector[r].isLessThan(vector[p])) {
                l = left(p);
                j = (vector[l].isLessThan(vector[r]) ? l : r);
            } else {
                l = left(p);

                //Check if left child exists and is less than its parent
                if ((l < size) && vector[l].isLessThan(vector[p])) {
                    j = l;
                }
            }
            if (j > -1) {
                swap(p, j);
            }
            p = j;
        } while (p > -1);
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Min heap is empty");
        }
        Item element = vector[0];
        vector[0] = vector[--size];
        shiftDown(0);
        return element.data;
    }

    public T pop() {
        return extractMin();
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Min heap is empty");
        }
        vector[0] = vector[--size];
        shiftDown(0);
    }

    private MinHeap<T> merge(MinHeap<T> other) {
        if (other.isEmpty()) {
            return this;
        }
        final int SIZE = other.size();
        Item[] temp = createArray(SIZE);
        for (int i = 0; i < SIZE; i++) {
            temp[i] = new Item(other.extractMin());
            this.insert(temp[i].data);
        }
        for (int i = 0; i < SIZE; i++) {
            other.insert(temp[i].data);
        }
        return this;
    }

    public static <E extends Comparable<E>> MinHeap<E> merge(MinHeap<E> first, MinHeap<E> second) {
        if ((first == null) || (second == null)) {
            throw new NullPointerException("The specified min heap is null");
        }
        return new MinHeap<E>(first.size() + second.size() + DEFAULT_CAPACITY).merge(first).merge(second);
    }

    private MinHeap<T> meld(MinHeap<T> other) {
        while (!other.isEmpty()) {
            this.insert(other.extractMin());
        }
        return this;
    }

    public static <E extends Comparable<E>> MinHeap<E> meld(MinHeap<E> first, MinHeap<E> second) {
        if ((first == null) || (second == null)) {
            throw new NullPointerException("The specified min heap is null");
        }
        return new MinHeap<E>(first.size() + second.size() + DEFAULT_CAPACITY).meld(first).meld(second);
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Item[] temp = createArray(capacity);
        final int SIZE = size;
        final int COMMA_SIZE = size - 1;
        for (int i = 0; i < SIZE; i++) {
            temp[i] = new Item(extractMin());
            sb.append(temp[i].data);
            if (i < COMMA_SIZE) {
                sb.append(", ");
            }
        }
        sb.append("]");
        size = SIZE;
        vector = temp;
        return sb.toString();
    }
}