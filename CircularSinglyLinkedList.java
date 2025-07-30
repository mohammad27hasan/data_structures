class CircularSinglyLinkedList<T> {
    
    private class Node {
        T data;
        Node next;
        Node(T val) {
            data = val;
            next = null;
        }
    }

    private Node tail;
    private int size;
    CircularSinglyLinkedList() {
        tail = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        if  (tail == null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        if (tail == null) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            Node iterator = tail.next;
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

    boolean contains(Object o) {
        if (o == null || tail == null) {
            return false;
        } else {
            Node iterator = tail.next;
            do {
                if (o.equals(iterator.data)) {
                    return true;
                }
                iterator = iterator.next;
            } while (iterator != tail.next);
            return false;
        }
    }

    T headElement() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularSinglyLinkedList is empty");
        }
        return tail.next.data;
    }

    T tailElement() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularSinglyLinkedList is empty");
        }
        return tail.data;
    }

    T getBeginning() {
        return headElement();
    }

    T getAt(int index) {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularSinglyLinkedList is empty");
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Must be in range [0, " + (size - 1) + "]");
        }
        Node iterator = tail.next;
        for (int i = 0; i < size; i++) {
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

    void replace(T element, T item) {
        if (element == null || tail == null) {
            throw new java.util.NoSuchElementException(element + " is not present in CircularSinglyLinkedList");
        } else if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node iterator = tail.next;
            do {
                if (element.equals(iterator.data)) {
                    iterator.data = item;
                }
                iterator = iterator.next;
            } while (iterator != tail.next);
            throw new java.util.NoSuchElementException(element + " is not present in CircularSinglyLinkedList");
        }
    }

    void clear() {
        if (tail == null) {
            return;
        } else {
            Node iterator = tail.next;
            Node temp = tail.next;
            do {
                iterator = iterator.next;
                temp.data = null;
                temp.next = null;
                temp = iterator;
            } while (iterator != tail.next);
            tail = null;
            size = 0;
        }
    }

    void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (tail == null) {
            Node newNode = new Node(item);
            tail = newNode;
            tail.next = tail;
        } else {
            Node newNode = new Node(item);
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size = size + 1;
    }

    void insertAfter(T element, T item) {
        if (element == null || tail == null) {
            throw new java.util.NoSuchElementException(element + " is present in CircularSinglyLinkedList");
        } else if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node iterator = tail.next;
            do {
                if (element.equals(iterator.data)) {
                    Node newNode = new Node(item);
                    newNode.next = iterator.next;
                    iterator.next = newNode;
                    size = size + 1;
                    if (iterator == tail) {
                        tail = newNode;
                    }
                    return;
                }
                iterator = iterator.next;
            } while (iterator != tail.next);
            throw new java.util.NoSuchElementException(element + " is present in CircularSinglyLinkedList");
        }
    }

    void insertEnd(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (tail == null) {
            Node newNode = new Node(item);
            tail = newNode;
            tail.next = tail;
        } else {
            Node newNode = new Node(item);
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size = size + 1;
    }

    void insert(T item) {
        insertEnd(item);
    }

    void removeBeginning() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularSinglyLinkedList is empty");
        } else if (tail == tail.next) {
            tail.data = null;
            tail.next = null;
            tail = null;
        } else {
            Node temp = tail.next;
            tail.next = temp.next;
            temp.data = null;
            temp.next = null;
            temp = null;
        }
        size = size - 1;
    }

    void removeAfter(T element) {
        if (tail == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in CircularSinglyLinkedList");
        } else {
            Node iterator = tail.next;
            do {
                if (element.equals(iterator.data)) {
                    if (iterator == tail) {
                        throw new java.util.NoSuchElementException("After " + element + " there is no element");
                    } else {
                        Node temp = iterator.next;
                        iterator.next = temp.next;
                        temp.data = null;
                        temp.next = null;
                        temp = null;
                        size = size - 1;
                        return;
                    }
                }
                iterator = iterator.next;
            } while (iterator != tail.next);
            throw new java.util.NoSuchElementException(element + " is not present in CircularSinglyLinkedList");
        }
    }

    void removeEnd() {
        if (tail == null) {
            throw new java.util.NoSuchElementException("CircularSinglyLinkedList is empty");
        } else if (tail == tail.next) {
            tail.data = null;
            tail.next = null;
            tail = null;
        } else {
            Node iterator = tail.next;
            while (iterator.next != tail) {
                iterator = iterator.next;
            }
            iterator.next = tail.next;
            tail.data = null;
            tail.next = null;
            tail = iterator;
        }
        size = size - 1;
    }
}