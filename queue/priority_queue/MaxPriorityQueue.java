package ds.queue.priority;

public class MaxPriorityQueue<T> {
    private class Entry {
        T data;
        int priority;
        Entry next;
        Entry(T val, int priority) {
            data = val;
            this.priority = priority;
            next = null;
        }
    }
    private static final int DEFAULT_CAPACITY = 14;
    private Entry[] vector;
    private int capacity;
    private int size;
    private int index;
    public MaxPriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public MaxPriorityQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity is less than 1");
        }
        capacity = initialCapacity;
        vector = createEntryArray(capacity);
        size = 0;
        index = -1;
    }

    @SuppressWarnings("unchecked")
    private Entry[] createEntryArray(int length) {
        return (Entry[])(java.lang.reflect.Array.newInstance(Entry.class, length));
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

    private void grow() {
        capacity = capacity + DEFAULT_CAPACITY;
        Entry[] temp = createEntryArray(capacity);
        final int LENGTH = index + 1;
        for (int i = 0; i < LENGTH; i++) {
            temp[i] = vector[i];
        }
        vector = temp;
    }

    private int parent(int i) {
        return ((i - 1) / 2);
    }

    private void swap(int i, int j) {
        Entry temp = vector[i];
        vector[i] = vector[j];
        vector[j] = temp;
    }

    private void bubbleUp(int i) {
        int p = parent(i);
        while (vector[i].priority > vector[p].priority) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }

    public void insert(T item, int priority) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        }
        if (priority < 1) {
            throw new IllegalArgumentException("Priority is less than 1");
        }
        if (isFull()) {
            grow();
        }
        final int LENGTH = index + 1;
        for (int i = 0; i < LENGTH; i++) {
            if (vector[i].priority == priority) {
                Entry iterator = vector[i];
                while (iterator.next != null) {
                    iterator = iterator.next;
                }
                iterator.next = new Entry(item, priority);
                size = size + 1;
                return;
            }
        }
        vector[++index] = new Entry(item, priority);
        bubbleUp(index);
        size = size + 1;
    }

    public void enqueue(T item, int priority) {
        insert(item, priority);
    }

    public T maxElement() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Max priority queue is empty");
        }
        return vector[0].data;
    }

    public T findMax() {
        return maxElement();
    }

    private int left(int i) {
        return (2 * i + 1);
    }

    private int right(int i) {
        return (2 * i + 2);
    }

    private void bubbleDown(int p) {
        int j = 0;
        int l = 0;
        int r = 0;
        final int LENGTH = index + 1;
        do {
            j = -1;
            r = right(p);
            if ((r < LENGTH) && (vector[r].priority > vector[p].priority)) {
                l = left(p);
                j = (vector[l].priority > vector[r].priority) ? l : r;
            } else {
                l = left(p);
                if ((l < LENGTH) && (vector[l].priority > vector[p].priority)) {
                    j = l;
                }
            }
            if (j > -1) {
                swap(j, p);
            }
            p = j;
        } while (p > -1);
    }

    public T extractMax() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Max priority queue is empty");
        }
        Entry entry = vector[0];
        if (entry.next != null) {
            vector[0] = entry.next;
            size = size - 1;
            return entry.data;
        }
        vector[0] = vector[index--];
        bubbleDown(0);
        size = size - 1;
        return entry.data;
    }

    public void removeMax() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Max priority queue is empty");
        }
        if (vector[0].next != null) {
            vector[0] = vector[0].next;
            size = size - 1;
            return;
        }
        vector[0] = vector[index--];
        bubbleDown(0);
        size = size - 1;
    }

    public T dequeue() {
        return extractMax();
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Entry[] temp = createEntryArray(capacity);
        final int SIZE = size;
        final int LENGTH = index + 1;
        final int COMMA_SIZE = size - 1;
        final int INDEX = index;
        for (int i = 0; i < SIZE; i++) {
            if (i < LENGTH) {
                temp[i] = vector[0];
            }
            sb.append(extractMax());
            if (i < COMMA_SIZE) {
                sb.append(", ");
            }
        }
        size = SIZE;
        index = INDEX;
        vector = temp;
        sb.append("]");
        return sb.toString();
    }
}