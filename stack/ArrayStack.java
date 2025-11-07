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

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        final int SIZE = size();
        final int COMMA_SIZE = SIZE - 1;
        for (int i = 0; i < SIZE; i++) {
            sb.append(array.get(i));
            if (i < COMMA_SIZE) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}