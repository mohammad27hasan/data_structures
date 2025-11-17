/*
* Abstract data type: Array stack
* Version: 1.0.8
* Author: Mohammad Hasan
*/
package ds.stack;

public class ArrayStack<T> {
    private int top;
    private java.util.ArrayList<T> array;
    private int capacity;

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Specified capacity is less than 1");
        }
        top = -1;
        array = new java.util.ArrayList<T>(capacity);
        this.capacity = capacity;
    }

    public int size() {
        return (top + 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == (capacity - 1));
    }

    public void push(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        if (isFull()) {
            throw new IllegalStateException("Array stack is full");
        }
        top++;
        array.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return array.remove(top--);
    }

    public T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return array.get(top);
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (i < top) {
            sb.append(array.get(i) + ", ");
            i++;
        }
        sb.append(array.get(i) + "]");
        return sb.toString();
    }
}