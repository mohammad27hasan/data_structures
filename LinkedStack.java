class LinkedStack<T> {
    private class Node {
        T data;
        Node next;
        Node(T val) {
            data = val;
            next = null;
        }
    }

    private Node head;
    private int size;
    LinkedStack() {
        head = null;
        size = 0;
    }

    int size() {
        return size;
    }

    private void reverse() {
        Node prevPtr = null;
        Node currPtr = head;
        Node nextPtr = null;
        while (currPtr != null) {
            nextPtr = currPtr.next;
            currPtr.next = prevPtr;
            prevPtr = currPtr;
            currPtr = nextPtr;            
        }
        head = prevPtr;
    }

    public String toString() {
        reverse();
        Node iterator = head;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(iterator.data);
            if (i < size - 1) {
                sb.append(", ");
            }
            iterator = iterator.next;
        }
        sb.append("]");
        reverse();
        return sb.toString();
    }

    void push(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node newHead = new Node(item);
            newHead.next = head;
            head = newHead;
            size = size + 1;
        }
    }

    T pop() {
        if (head == null) {
            throw new java.util.EmptyStackException();
        } else {
            T item = head.data;
            head = head.next;
            size = size - 1;
            return item;
        }
    }

    T peek() {
        if (head == null) {
            throw new java.util.EmptyStackException();
        } else {
            return head.data;
        }
    }
}