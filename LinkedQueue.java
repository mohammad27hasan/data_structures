class LinkedQueue<T> {
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
    LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        if (front == null) {
            return true;
        } else {
            return false;
        }
    }

    void enqueue(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (front == null) {
            Node newNode = new Node(item);
            front = newNode;
            rear = newNode;
        } else {
            Node newNode = new Node(item);
            rear.next = newNode;
            rear = newNode;
        }
        size = size + 1;
    }

    T dequeue() {
        if (front == null) {
            throw new java.util.NoSuchElementException("LinkedQueue is empty");
        } else {
            Node temp = front;
            T element = front.data;
            front = front.next;
            size = size - 1;
            temp.data = null;
            temp.next = null;
            temp = null;
            return element;
        }
    }

    T front() {
        if (front == null) {
            throw new java.util.NoSuchElementException("LinkedQueue is empty");
        } else {
            return front.data;
        }
    }

    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            Node iterator = front;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(iterator.data);
                if (i < size - 1) {
                    sb.append(", ");
                }
                iterator = iterator.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }
}