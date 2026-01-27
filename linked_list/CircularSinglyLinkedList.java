/*
* Data structure: Linked list
* Abstract data type: Circular singly linked list
* Version: 1.0.3
* Author: Mohammad Hasan
*/
package ds.linked;

public class CircularSinglyLinkedList<T> {
    private class Node {
        T data;
        Node next;

        Node(T value) {
            data = value;
            next = this;
        }
    }

    private int size;
    private Node tail;
    
    public CircularSinglyLinkedList() {
        size = 0;
        tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (tail == null);
    }

    private Node search(T key) {
        if (isEmpty()) {
            return null;
        }
        Node node = tail.next;
        while (node != tail) {
            if (key.equals(node.data)) {
                return node;
            }
            node = node.next;
        }
        return (key.equals(node.data) ? node : null);
    }

    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        return (search(element) != null);
    }

    public T getBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return tail.next.data;
    }

    public T getEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
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

    public void setAt(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
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
        node.data = element;
    }

    public void insertBeginning(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    public void insertEnd(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertAt(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            insertBeginning(element);
        } else if (index == (size - 1)) {
            insertEnd(element);
        } else {
            Node newNode = new Node(element);
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

    public void insert(T element) {
        insertEnd(element);
    }

    public void removeBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
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
            throw new java.util.NoSuchElementException("Size: " + size);
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

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        Node forward = null;
        Node current = tail.next;
        Node backward = tail;
        while (current != tail) {
            forward = current.next;
            current.next = backward;
            backward = current;
            current = forward;
        }
        tail = current.next;
        current.next = backward;
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