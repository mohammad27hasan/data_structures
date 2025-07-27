class DoublyLinkedList<T> {
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
    private Node head;
    private Node tail;
    private int size;
    DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        Node iterator = head;
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        int len = size - 1;
        while (iterator != null) {
            sb.append(iterator.data);
            if (i < len) {
                sb.append(", ");
            }
            i = i + 1;
            iterator = iterator.next;
        }
        sb.append("]");
        return sb.toString();
    }

    boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Node iterator = head;
        while (iterator != null) {
            if (o.equals(iterator.data)) {
                return true;
            }
            iterator = iterator.next;
        }
        return false;
    }

    private void checkElement(T element) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in DoublyLinkedList");
        }
    }

    private void checkItem(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        }
    }

    private void checkElements(T element, T item) {
        checkElement(element);
        checkItem(item);
    }

    T headElement() {
        if (head == null) {
            throw new java.util.NoSuchElementException("DoublyLinkedList is empty");
        }
        return head.data;
    }

    T tailElement() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("DoublyLinkedList is empty");
        }
        return tail.data;
    }

    T getBeginning() {
        return headElement();
    }

    T getAt(int index) {
        if (head == null) {
            throw new java.util.NoSuchElementException("DoublyLinkedList is empty");
        } else if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Must be in range [0, " + (size() - 1) + "]");
        }
        Node iterator = head;
        for (int i = 0; i < size(); i++) {
            if (i == index) {
                return iterator.data;
            }
            iterator = iterator.next;
        }
        return null;
    }

    T getEnd() {
        return tailElement();
    }

    void insertAfter(T element, T item) {
        checkElements(element, item);
        Node node = head;
        while (node != null) {
            if (element.equals(node.data)) {
                Node newNode = new Node(item);
                newNode.prev = node;
                if (node.next == null) {
                    newNode.next = null; // (not always necessary)
                    tail = newNode;
                } else {
                    newNode.next = node.next;
                    node.next.prev = newNode;
                }
                node.next = newNode;
                size = size + 1;
                return;
            }
            node = node.next;
        }
        throw new java.util.NoSuchElementException(element + " is not present in DoublyLinkedList");
    }

    void insertBefore(T element, T item) { 
        checkElements(element, item);
        Node node = head;
        while (node != null) {
            if (element.equals(node.data)) {
                Node newNode = new Node(item);
                newNode.next = node;
                if (node.prev == null) {
                    newNode.prev = null; // (not always necessary)
                    head = newNode;
                } else {
                    newNode.prev = node.prev;
                    node.prev.next = newNode;
                }
                node.prev = newNode;
                size = size + 1;
                return;
            }
            node = node.next;
        }
        throw new java.util.NoSuchElementException(element + " is not present in DoublyLinkedList");
    }

    void insertBeginning(T item) {   
        if (head == null) {
            checkItem(item);
            Node newNode = new Node(item);
            head = newNode;
            tail = newNode;
            size = size + 1;
        } else {
            insertBefore(head.data, item);
        }
    }

    void insertEnd(T item) {
        if (tail == null) {
            insertBeginning(item);
        } else {
            checkItem(item);
            Node newNode = new Node(item);
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            size = size + 1;
        }
    }

    void insert(T item) {
        insertEnd(item);
    }

    void removeBeginning() {
        if (head == null) {
            throw new java.util.NoSuchElementException("DoublyLinkedList is empty");
        }
        remove(head);
    }

    void remove(T element) {
        checkElement(element);
        Node node = head;
        while (node != null) {
            if (element.equals(node.data)) {
                remove(node);
                return;
            }
            node = node.next;
        }
        throw new java.util.NoSuchElementException(element + " is not present in DoublyLinkedList");
    }

    void removeEnd() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("DoublyLinkedList is empty");
        }
        remove(tail);
    }

    private void remove(Node node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        size = size - 1;
    }

    void replace(T element, T item) {
        checkElements(element, item);
        Node iterator = head;
        while (iterator != null) {
            if (element.equals(iterator.data)) {
                iterator.data = item;
                return;
            }
            iterator = iterator.next;
        }
        throw new java.util.NoSuchElementException(element + " is not present in DoublyLinkedList");
    }

    void clear() {
        Node iterator = head;
        while (iterator != null) {
            head = head.next;
            iterator.prev = null;
            iterator.data = null;
            iterator.next = null;
            iterator = head;
        }
        tail = null;
    }
}