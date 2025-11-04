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
    private Node front;
    private Node rear;
    private int size;
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

    public void enqueue(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
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
            throw new IllegalStateException("Linked queue is empty");
        }
        T element = front.data;
        if (front == rear) { // Has one element
            rear = null;
        }
        front = front.next;
        size--;
        return element;
    }

    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Linked queue is empty");
        }
        return front.data;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node node = front;
        while (node != null) {
            sb.append(node.data);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}