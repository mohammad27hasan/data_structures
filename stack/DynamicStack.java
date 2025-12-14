/*
* Data structure: Stack
* Abstract data type: Dynamic stack
* Version: 1.0.5
* Author: Mohammad Hasan
*/
package ds.stack;

public class DynamicStack<T> {
    private java.util.ArrayList<T> array;
    private int top;
    
    public DynamicStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        array = new java.util.ArrayList<T>(capacity);
        top = -1;
    }

    public DynamicStack() {
        this(10);
    }

    public int size() {
        return (top + 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        top++;
        array.add(element);
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
        }
        sb.append(array.get(i) + "]");
        return sb.toString();
    }
}