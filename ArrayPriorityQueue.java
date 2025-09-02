class ArrayPriorityQueue<T> {
    private class Item {
        T data;
        int priority;
        Item(T val, int priority) {
            data = val;
            this.priority = priority;
        }
    }
    private Item[] buffer;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    ArrayPriorityQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity is less than 1");
        }
        buffer = (Item[])(java.lang.reflect.Array.newInstance(Item.class, capacity));
        this.capacity = capacity;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    boolean isFull() {
        return (size == capacity);       
    }

    private void insertAt(int index, Item newItem) {
        for (int i = size++; i > index; i--) {
            buffer[i] = buffer[i - 1];
        }
        buffer[index] = newItem;
    }

    void insert(T element, int priority) {
        if (element == null) {
            throw new NullPointerException("The specified element is null");
        }
        if (priority < 1) {
            throw new IllegalArgumentException("priority is less than 1");
        }
        if (isFull()) {
            throw new IllegalStateException("ArrayPriorityQueue is full");
        }
        Item newItem = new Item(element, priority);
        int index = 0;
        while ((index < size) && (buffer[index].priority <= priority)) {
            index = index + 1;
        }
        insertAt(index, newItem);
    }

    private void removeAt(int index) {
        int len = --size;
        for (int i = index; i < len; i++) {
            buffer[i] = buffer[i + 1];
        }
    }

    T extract() {
        if (isEmpty()) {
            throw new IllegalStateException("ArrayPriorityQueue is empty");
        }
        Item highest = buffer[0];
        removeAt(0);
        return highest.data;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int numberOfCommas = size - 1;
        for (int i = 0; i < size; i++) {
            sb.append(buffer[i].data);
            if (i < numberOfCommas) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}