/*
* Data structure: Deque
* Abstract data type: Linked deque
* Version: 1.0.3
* Author: Mohammad Hasan
*/
package ds.deque;

public class LinkedDeque<T> {
    private class Node {
        Node prev;
        T data;
        Node next;

        Node(T val) {
            prev = null;
            data = val;
            next = null;
        }
    }

    private int size;
    private Node front;
    private Node rear;
    
    public LinkedDeque() {
        size = 0;
        front = null;
        rear = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void pushBack(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public void pushFront(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
        }
        front = newNode;
        size++;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        T element = rear.data;
        rear = rear.prev;
        rear.next = null;
        size--;
        return element;
    }

    public T popFront() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        T element = front.data;
        front = front.next;
        front.prev = null;
        size--;
        return element;
    }

    public T back() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return rear.data;
    }

    public T front() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return front.data;
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
        Node node = front;
        while (node != rear) {
            sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}