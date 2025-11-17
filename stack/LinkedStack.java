/*
* Abstract data type: Linked stack
* Version: 1.0.4
* Author: Mohammad Hasan
*/
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
        Node node = bottom;
        while (node != top) {
            sb.append(node.data + ", ");
            node = node.up;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}