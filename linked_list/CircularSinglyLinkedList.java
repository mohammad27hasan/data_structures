/*
* Abstract data type: Circular singly linked list
* Version: 1.0.1
* Author: Mohammad Hasan
*/
package ds.linked;

public class CircularSinglyLinkedList<T> {
    private class Node {
        T data;
        Node next;

        Node(T val) {
            data = val;
            next = this;
        }
    }

    private Node tail;
    private int size;
    
    public CircularSinglyLinkedList() {
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (tail == null);
    }

    private Node search(Object item) {
        if (isEmpty() || (item == null)) {
            return null;
        }
        Node node = tail.next;
        while ((node != tail) && !node.data.equals(item)) {
            node = node.next;
        }
        return (node.data.equals(item) ? node : null);
    }

    public boolean contains(Object item) {
        return ((search(item) == null) ? false : true);
    }

    public T getBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular singly linked list is empty");
        }
        return tail.next.data;
    }

    public T getEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular singly linked list is empty");
        }
        return tail.data;
    }

    public T getAt(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node node = tail.next;
        int idx = 0;
        while (node != tail) {
            if (idx == index) {
                break;
            }
            idx++;
            node = node.next;
        }
        return node.data;
    }

    public void setAt(int index, T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node node = tail.next;
        int idx = 0;
        while (node != tail) {
            if (idx == index) {
                break;
            }
            idx++;
            node = node.next;
        }
        node.data = item;
    }

    public void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    public void insertEnd(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertAt(int index, T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            insertBeginning(item);
        } else if (index == (size - 1)) {
            insertEnd(item);
        } else {
            Node newNode = new Node(item);
            Node node = tail.next;
            int idx = 1;
            while (node != tail) {
                if (idx == index) {
                    newNode.next = node.next;
                    node.next = newNode;
                    break;
                }
                idx++;
                node = node.next;
            }
            size++;
        }
    }

    public void insert(T item) {
        insertEnd(item);
    }

    public void removeBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular singly linked list is empty");
        }
        if (tail == tail.next) {
            tail = null;
        } else {
            tail.next = tail.next.next;
        }
        size--;
    }

    public void removeEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular singly linked list is empty");
        }
        if (tail == tail.next) {
            tail = null;
        } else {
            Node node = tail.next;
            while (node.next != tail) {
                node = node.next;
            }
            node.next = tail.next;
            tail = node;
        }
        size--;
    }

    public void removeAt(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            removeBeginning();
        } else if (index == (size - 1)) {
            removeEnd();
        } else {
            Node node = tail.next;
            int idx = 1;
            while (node != tail) {
                if (idx == index) {
                    node.next = node.next.next;
                    break;
                }
                idx++;
                node = node.next;
            }
            size--;
        }
    }

    public void clear() {
        while (!isEmpty()) {
            removeBeginning();
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node node = tail.next;
        while (node != tail) {
            sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}