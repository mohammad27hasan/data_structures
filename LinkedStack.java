class LinkedStack<T> {
    private class Node {
        private T data;
        private Node next;
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

    private Node reverse(Node head) {
        Node prevPtr = null;
        Node currPtr = head;
        Node nextPtr = null;
        while (currPtr != null) {
            nextPtr = currPtr.next;
            currPtr.next = prevPtr;
            prevPtr = currPtr;
            currPtr = nextPtr;            
        }
        return prevPtr;
    }

    public String toString() {
        head = reverse(head);
        Node iterator = head;
        String s = "[";
        for (int i = 0; iterator != null; i++) {
            s = s.concat(String.valueOf(iterator.data));
            if (i < size - 1) {
                s = s.concat(", ");
            }
            iterator = iterator.next;
        }
        s = s.concat("]");
        head = reverse(head);
        return s;
    }

    void push(T item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else {
            Node newHead = new Node();
            newHead.data = item;
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