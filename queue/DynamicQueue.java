package ds.queue;

public class DynamicQueue<T> {
    private int front;
    private int rear;
    private int capacity;
    private java.util.ArrayList<T> array;
    private int size;
    public DynamicQueue() {
        this(10);
    }

    public DynamicQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Specified capacity is less than 1");
        }
        front = -1;
        rear = -1;
        capacity = initialCapacity;
        array = new java.util.ArrayList<T>(java.util.Collections.nCopies(capacity, null));
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    private boolean isFull() {
        return (front == ((rear + 1) % capacity));
    }

    private void grow() {
        int cap = capacity;
        capacity = capacity * 3 / 2 + 1;
        var temp = new java.util.ArrayList<T>(java.util.Collections.nCopies(capacity, null));
        int i = front;
        int j = 0;
        while (i != rear) {
            temp.set(j, array.get(i));
            i = (i + 1) % cap;
            j++;
        }
        front = 0;
        rear = j;
        size = j + 1;
        array = temp;
    }

    public void enqueue(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        } 
        if (isFull()) {
            grow();
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        array.set(rear, item);
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Dynamic queue is empty");
        }
        T element = array.get(front);
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return element;
    }

    public T front() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Dynamic queue is empty");
        }
        return array.get(front);
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int commaSize = size - 1;
        int i = front;
        while (i != rear) {
            sb.append(array.get(i));
            if (commaSize > 0) {
                sb.append(", ");
                commaSize--;
            }
            i = (i + 1) % capacity;
        }
        sb.append(array.get(i) + "]");
        return sb.toString();
    }
}