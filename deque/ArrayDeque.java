package ds.deque;

public class ArrayDeque<T> {
    private int front;
    private int rear;
    private int capacity;
    private java.util.ArrayList<T> array;
    private int size;
    public ArrayDeque(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Specified capacity is less than 1");
        }
        front = -1;
        rear = -1;
        this.capacity = capacity;
        array = new java.util.ArrayList<T>(java.util.Collections.nCopies(capacity, null));
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    public boolean isFull() {
        return (front == ((rear + 1) % capacity));
    }

    public void pushBack(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        if (isFull()) {
            throw new IllegalStateException("Array deque is full");
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        array.set(rear, item);
        size++;
    }

    public void pushFront(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        if (isFull()) {
            throw new IllegalStateException("Array deque is full");
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = capacity - 1;
        } else {
            front--;
        }
        array.set(front, item);
        size++;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Array deque is empty");
        }
        T element = array.get(rear);
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (rear == 0) {
            rear = capacity - 1;
        } else {
            rear--;
        }
        size--;
        return element;
    }

    public T popFront() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Array deque is empty");
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
            throw new java.util.NoSuchElementException("Array deque is empty");
        }
        return array.get(front);
    }

    public T back() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Array deque is empty");
        }
        return array.get(rear);
    }

    public void clear() {
        while (!isEmpty()) {
            popBack();
        }
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