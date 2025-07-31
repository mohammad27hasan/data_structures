class CircularDoublyLinkedList<T> {
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
    CircularDoublyLinkedList() {
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
        } else {
            return false;
        }
    }

    void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (head == null) {
            Node newNode = new Node(item);
            newNode.prev = newNode;
            newNode.next = newNode;
            head = newNode;
            tail = newNode;
        } else {
            Node newNode = new Node(item);
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size = size + 1;
    }

    void insertEnd(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (tail == null) {
            Node newNode = new Node(item);
            newNode.prev = newNode;
            newNode.next = newNode;
            head = newNode;
            tail = newNode;
        } else {
            Node newNode = new Node(item);
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail.next = newNode;
            tail = newNode;
        }
        size = size + 1;
    }

    void insertAfter(T element, T item) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        } else if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node iterator = head;
            do {
                if (element.equals(iterator.data)) {
                    Node newNode = new Node(item);
                    newNode.prev = iterator;
                    newNode.next = iterator.next;
                    iterator.next.prev = newNode;
                    iterator.next = newNode;
                    if (iterator == tail) {
                        tail = newNode;
                    }
                    size = size + 1;
                    return;
                }
                iterator = iterator.next;
            } while (iterator != head);
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        }
    }

    void insertBefore(T element, T item) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        } else if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node iterator = head;
            do {
                if (element.equals(iterator.data)) {
                    Node newNode = new Node(item);
                    newNode.next = iterator;
                    newNode.prev = iterator.prev;
                    iterator.prev.next = newNode;
                    iterator.prev = newNode;
                    if (iterator == head) {
                        head = newNode;
                    }
                    size = size + 1;
                    return;
                }
                iterator = iterator.next;
            } while (iterator != head);
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        }
    }

    void insert(T item) {
        insertEnd(item);
    }

    void removeBeginning() {
        if (head == null) {
            throw new java.util.NoSuchElementException("CircularDoublyLinkedList is empty");
        } else {
            Node temp = head;
            head = head.next;
            head.prev = tail;
            tail.next = head;
            temp.prev = null;
            temp.data = null;
            temp.next = null;
            temp = null;
            size = size - 1;
        }
    }

    void removeEnd() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularDoublyLinkedList is empty");
        } else {
            Node temp = tail;
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
            temp.prev = null;
            temp.data = null;
            temp.next = null;
            temp = null;
            size = size - 1;
        }
    }

    void remove(T element) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        } else {
            Node iterator = head;
            do {
                if (element.equals(iterator.data)) {
                    iterator.prev.next = iterator.next;
                    iterator.next.prev = iterator.prev;
                    if (iterator == head) {
                        head = iterator.next;
                    }
                    if (iterator == tail) {
                        tail = iterator.prev;
                    }
                    size = size - 1;
                    return;
                }
                iterator = iterator.next;
            } while (iterator != head);
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        }
    }

    T headElement() {
        if (head == null) {
            throw new java.util.NoSuchElementException("CircularDoublyLinkedList is empty");
        } else {
            return head.data;
        }
    }

    T tailElement() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularDoublyLinkedList is empty");
        } else {
            return tail.data;
        }
    }

    T getBeginning() {
        return headElement();
    }

    T getEnd() {
        return tailElement();
    }

    T getAt(int index) {
        if (head == null) {
            throw new java.util.NoSuchElementException("CircularDoublyLinkedList is empty");
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Must be in range [0, " + (size - 1) + "]");
        } else {
            Node iterator = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return iterator.data;
                }
                iterator = iterator.next;
            }
        }
        return null;
    }

    int indexOf(Object o) {
        if (o == null || head == null) {
            return -1;
        } else {
            Node iterator = head;
            for (int i = 0; i < size; i++) {
                if (o.equals(iterator.data)) {
                    return i;
                }
                iterator = iterator.next;
            }
            return -1;
        }
    }

    boolean contains(Object o) {
        if (o == null || head == null) {
            return false;
        } else {
            Node iterator = head;
            do {
                if (o.equals(iterator.data)) {
                    return true;
                }
                iterator = iterator.next;
            } while (iterator != head);
            return false;
        }
    }

    void replace(T element, T item) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        } else if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node iterator = head;
            do {
                if (element.equals(iterator.data)) {
                    iterator.data = item;
                    return;
                }
                iterator = iterator.next;
            } while (iterator != head);
            throw new java.util.NoSuchElementException(element + " is not present in CircularDoublyLinkedList");
        }
    }

    void clear() {
        if (head == null) {
            return;
        }
        Node iterator = head;
        for (int i = 0; i < size; i++) {
            head = head.next;
            iterator.prev = null;
            iterator.data = null;
            iterator.next = null;
            iterator = head;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public String toString() {
        if (head == null) {
            return "[]";
        } else {
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
            return sb.toString();
        }
    }
}