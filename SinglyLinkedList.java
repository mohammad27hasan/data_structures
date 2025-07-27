class SinglyLinkedList<T> {
    private class Node {
        T data;
        Node next;
    }

    private Node head;
    private int size;
    SinglyLinkedList() {
        head = null;
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

    public String toString() {
        Node iterator = head;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; iterator != null; i++) {
            sb.append(iterator.data);
            iterator = iterator.next;
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    boolean contains(Object o) {
        if (o == null) {
            return false;
        } else {
            Node iterator = head;
            while (iterator != null) {
                if (o.equals(iterator.data)) {
                    return true;
                }
                iterator = iterator.next;
            }
            return false;
        }
    }

    T headElement() {
        if (head == null) {
            throw new java.util.NoSuchElementException("SinglyLinkedList is empty");
        } else {
            return head.data;
        }
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
        if (head == null) {
            throw new java.util.NoSuchElementException("SinglyLinkedList is empty");
        } else {
            Node iterator = head;
            while (iterator.next != null) {
                iterator = iterator.next;
            }
            return iterator.data;
        }
    }

    void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else {
            Node newNode = new Node();
            newNode.data = item;
            newNode.next = head;
            head = newNode;
            size = size + 1;
        }
    }

    void insert(T item) {
        insertEnd(item);
    }

    void insertAfter(T element, T item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
        } else {
            boolean available = false;
            Node iterator = head;
            while (iterator != null) {
                if (element.equals(iterator.data)) {
                    Node newNode = new Node();
                    newNode.data = item;
                    newNode.next = iterator.next;
                    iterator.next = newNode;
                    size = size + 1;
                    available = true;
                    break;
                }
                iterator = iterator.next;
            }
            if (!available) {
                throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
            }
        }
    }

    void insertEnd(T item) {
        if (head == null) {
            insertBeginning(item);
        } else if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else {
            Node newNode = new Node();
            newNode.data = item;
            newNode.next = null;
            Node iterator = head;
            while (iterator.next != null) {
                iterator = iterator.next;
            }
            iterator.next = newNode;
            size = size + 1;
        }
    }

    void removeBeginning() {
        if (head == null) {
            throw new java.util.NoSuchElementException("SinglyLinkedList is empty");
        } else {
            Node obsoleteNode = head;
            head = head.next;
            obsoleteNode.data = null;
            obsoleteNode.next = null;
            obsoleteNode = null;
            size = size - 1;
        }
    }

    void removeAfter(T element) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
        } else {
            boolean available = false;
            Node iterator = head;
            while (iterator != null) {
                if (element.equals(iterator.data)) {
                    if (iterator.next != null) {
                        Node obsoleteNode = iterator.next;
                        iterator.next = iterator.next.next;
                        obsoleteNode.data = null;
                        obsoleteNode.next = null;
                        obsoleteNode = null;
                        size = size - 1;
                        available = true;
                        break;
                    } else {
                        throw new java.util.NoSuchElementException("After " + element + " there is no element");
                    }
                }
                iterator = iterator.next;
            }
            if (!available) {
                throw new java.util.NoSuchElementException(element + "is not present in SinglyLinkedList");
            }
        }
    }

    void removeEnd() {
        if (head == null) {
            throw new java.util.NoSuchElementException("SinglyLinkedList is empty");
        } else if (head.next == null) {
            head.data = null;
            head = null;
        } else {
            Node iterator = head;
            while (iterator.next.next != null) {
                iterator = iterator.next;
            }
            iterator.next.data = null;
            iterator.next = null;
            iterator = null;
            size = size - 1;
        }
    }

    void remove(T element) {
        if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
        } else if (element.equals(head.data)) {
            removeBeginning();
        } else {
            boolean available = false;
            Node curr = head.next;
            Node prev = head;
            while (curr != null) {
                if (element.equals(curr.data)) {
                    prev.next = curr.next;
                    curr.data = null;
                    curr.next = null;
                    size = size - 1;
                    available = true;
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            if (!available) {
                throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
            }
        }
    }

    void replace(T element, T item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else if (head == null || element == null) {
            throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
        } else {
            boolean available = false;
            Node iterator = head;
            while (iterator != null) {
                if (element.equals(iterator.data)) {
                    iterator.data = item;
                    available = true;
                    break;
                }
                iterator = iterator.next;
            }
            if (!available) {
                throw new java.util.NoSuchElementException(element + " is not present in SinglyLinkedList");
            }
        }
    }

    void clear() {
        Node iterator = head;
        while (iterator != null) {
            head = head.next;
            iterator.data = null;
            iterator.next = null;
            iterator = head;
        }
        size = 0;
    }
}