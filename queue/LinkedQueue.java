/*
* Data structure: Queue
* Abstract data type: Linked queue
* Version: 1.0.4
* Author: Mohammad Hasan
*/
package ds.queue;

public class LinkedQueue<T> {
    private class Node {
        T data;
        Node next;

        Node(T val) {
            data = val;
            next = null;
        }
    }

    private int size;
    private Node front;
    private Node rear;

    public LinkedQueue() {
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

    public void enqueue(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Size: " + size);
        }
        T element = front.data;
        if (front == rear) {
            rear = null;
        }
        front = front.next;
        size--;
        return element;
    }

    public T front() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return front.data;
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
        Node node = front;
        while (node != rear) {
            sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}