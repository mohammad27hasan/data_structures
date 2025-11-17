/*
* Abstract data type: Linked deque
* Version: 1.0.2
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

    private Node front;
    private Node rear;
    private int size;
    
    public LinkedDeque() {
        front = null;
        rear = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void pushBack(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            front = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public void pushFront(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
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
            throw new java.util.NoSuchElementException("Linked deque is empty");
        }
        T element = rear.data;
        rear = rear.prev;
        rear.next = null;
        size--;
        return element;
    }

    public T popFront() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Linked deque is empty");
        }
        T element = front.data;
        front = front.next;
        front.prev = null;
        size--;
        return element;
    }

    public T back() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Linked deque is empty");
        }
        return rear.data;
    }

    public T front() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Linked deque is empty");
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