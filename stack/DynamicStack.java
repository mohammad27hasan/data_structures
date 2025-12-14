/*
* Data structure: Stack
* Abstract data type: Dynamic stack
* Version: 1.0.6
* Author: Mohammad Hasan
*/
package ds.stack;

public class DynamicStack<T> {
    private java.util.ArrayList<T> vector;
    private int top;
    
    public DynamicStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        vector = new java.util.ArrayList<T>(capacity);
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
        vector.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return vector.remove(top--);
    }

    public T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return vector.get(top);
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
            sb.append(vector.get(i) + ", ");
        }
        sb.append(vector.get(i) + "]");
        return sb.toString();
    }
}