class LinkedPriorityQueue<T> {
    private class Node {
        T data;
        int priority;
        Node next;
        Node(T val, int priority) {
            data = val;
            this.priority = priority;
            next = null;
        }
    }
    private Node head;
    private int size;
    LinkedPriorityQueue() {
        head = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return (head == null);
    }

    private void insertBeginning(Node newNode) {
        newNode.next = head;
        head = newNode;
    }

    private void insertAfter(Node node, Node newNode) {
        newNode.next = node.next;
        node.next = newNode;
    }

    void insert(T element, int priority) {
        if (element == null) {
            throw new NullPointerException("The specified element is null");
        }
        if (priority < 1) {
            throw new IllegalArgumentException("Priority is less than 1");
        }
        Node newNode = new Node(element, priority);
        if (isEmpty() || (priority < head.priority)) {
            insertBeginning(newNode);
        } else {
            Node iterator = head;
            while ((iterator.next != null) && (iterator.next.priority <= priority)) {
                iterator = iterator.next;
            }
            insertAfter(iterator, newNode);
        }
        size = size + 1;
    }

    void destroy(Node node) {
        node.data = null;
        node.priority = 0;
        node.next = null;
        node = null;
    }

    T extract() {
        if (isEmpty()) {
            throw new IllegalStateException("LinkedPriorityQueue is empty");
        }
        Node temp = head;
        T element = head.data;
        head = head.next;
        destroy(temp);
        size = size - 1;
        return element;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node iterator = head;
        while (iterator != null) {
            sb.append(iterator.data);
            if (iterator.next != null) {
                sb.append(", ");
            }
            iterator = iterator.next;
        }
        sb.append("]");
        return sb.toString();
    }
}