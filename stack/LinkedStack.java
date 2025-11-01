package ds.stack;

public class LinkedStack<T> {
    private class Node {
        Node up;
        T data;
        Node down;
        Node(T val) {
            up = null;
            data = val;
            down = null;
        }
    }
    private Node top;
    private Node bottom;
    private int size;
    public LinkedStack() {
        top = null;
        bottom = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public void push(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            top = newNode;
            bottom = newNode;
        } else {
            newNode.down = top;
            top.up = newNode;
            top = newNode;
        }
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        T element = top.data;
        top = top.down;
        if (top != null) {
            top.up = null;
        }
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return top.data;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node node = bottom;
        while (node != null) {
            sb.append(node.data);
            if (node.up != null) {
                sb.append(", ");
            }
            node = node.up;
        }
        sb.append("]");
        return sb.toString();
    }
}