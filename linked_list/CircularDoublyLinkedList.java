/*
* Abstract data type: Circular doubly linked list
* Version: 1.0.1
* Author: Mohammad Hasan
*/
package ds.linked;

public class CircularDoublyLinkedList<T> {
    private class Node {
        Node prev;
        T data;
        Node next;

        Node(T val) {
            prev = this;
            data = val;
            next = this;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CircularDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    private Node search(Object item) {
        if ((item == null) || isEmpty()) {
            return null;
        }
        Node node = head;
        while ((node != tail) && !node.data.equals(item)) {
            node = node.next;
        }
        return (node.data.equals(item) ? node : null);
    }

    public boolean contains(Object item) {
        return ((search(item) == null) ? false : true);
    }

    public void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
        }
        head = newNode;
        size++;
    }

    public void insertEnd(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void insertAt(int index, T item) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            insertBeginning(item);
        } else if (index == size) {
            insertEnd(item);
        } else {
            if (item == null) {
                throw new NullPointerException("Specified item is null");
            }
            Node newNode = new Node(item);
            Node node = head;
            int idx = 1;
            while (node != tail) {
                if (idx == index) {
                    newNode.prev = node;
                    newNode.next = node.next;
                    node.next.prev = newNode;
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
            throw new java.util.NoSuchElementException("Circular doubly linked list is empty");
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
    }

    public void removeEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular doubly linked list is empty");
        } 
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
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
            Node node = head.next;
            int idx = 1;
            while (node != tail) {
                if (idx == index) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    break;
                }
                idx++;
                node = node.next;
            }
            size--;
        }
    }

    public T getBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular doubly linked list is empty");
        }
        return head.data;
    }

    public T getEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Circular doubly linked list is empty");
        }
        return tail.data;
    }

    public T getAt(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node node = head;
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
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node node = head;
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

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        Node forward = null;
        Node current = head;
        Node backward = tail;
        while (current != tail) {
            forward = current.next;
            current.next = backward;
            current.prev = forward;
            backward = current;
            current = forward;
        }
        current.next = backward;
        current.prev = head;
        tail = head;
        head = current;
    }

    public void clear() {
        while (!isEmpty()) {
            removeEnd();
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node node = head;
        while (node != tail) {
            sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}