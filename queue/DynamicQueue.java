/*
* Data structure: Queue
* Abstract data type: Dynamic queue
* Version: 1.0.3
* Author: Mohammad Hasan
*/
package ds.queue;

public class DynamicQueue<T> {
    private java.util.ArrayList<T> vector;
    private int capacity;
    private int size;
    private int front;
    private int rear;

    public DynamicQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        vector = new java.util.ArrayList<T>(java.util.Collections.nCopies(initialCapacity, null));
        capacity = initialCapacity;
        size = 0;
        front = -1;
        rear = -1;
    }

    public DynamicQueue() {
        this(10);
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
        final int OLD_CAPACITY = capacity;
        capacity = capacity * 3 / 2 + 1;
        var newVector = new java.util.ArrayList<T>(java.util.Collections.nCopies(capacity, null));
        int i = front;
        int j = 0;
        while (i != rear) {
            newVector.set(j, vector.get(i));
            i = (i + 1) % OLD_CAPACITY;
            j++;
        }
        newVector.set(j, vector.get(i));
        front = 0;
        rear = j;
        size = j + 1;
        vector = newVector;
    }

    public void enqueue(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        } 
        if (isFull()) {
            grow();
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        vector.set(rear, element);
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Size: " + size);
        }
        T element = vector.get(front);
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
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return vector.get(front);
    }

    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int i = front;
        while (i != rear) {
            sb.append(vector.get(i) + ", ");
            i = (i + 1) % capacity;
        }
        sb.append(vector.get(i) + "]");
        return sb.toString();
    }
}