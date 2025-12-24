/*
* Data structrue: Deque
* Abstract data type: Array deque
* Version: 1.0.4
* Author: Mohammad Hasan
*/
package ds.deque;

public class ArrayDeque<T> {
    private java.util.ArrayList<T> array;
    private int capacity;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        array = new java.util.ArrayList<T>(java.util.Collections.nCopies(capacity, null));
        this.capacity = capacity;
        size = 0;
        front = -1;
        rear = -1;
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

    public void pushBack(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        if (isFull()) {
            throw new IllegalStateException("Size: " + (size + 1) + ", Capacity: " + capacity);
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        array.set(rear, element);
        size++;
    }

    public void pushFront(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        if (isFull()) {
            throw new IllegalStateException("Size: " + (size + 1) + ", Capacity: " + capacity);
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = capacity - 1;
        } else {
            front--;
        }
        array.set(front, element);
        size++;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
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
            throw new java.util.NoSuchElementException("Size: " + size);
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
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return array.get(front);
    }

    public T back() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
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
        int i = front;
        while (i != rear) {
            sb.append(array.get(i) + ", ");
            i = (i + 1) % capacity;
        }
        sb.append(array.get(i) + "]");
        return sb.toString();
    }
}