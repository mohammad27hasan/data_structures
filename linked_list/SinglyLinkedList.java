/*
* Abstract data type: Singly linked list
* Version: 1.0.7
* Author: Mohammad Hasan
*/
package ds.linked;

public class SinglyLinkedList<T> {
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

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    private Node search(Object item) {
        if (item == null) {
            return null;
        }
        Node node = head;
        while ((node != null) && !node.data.equals(item)) {
            node = node.next;
        }
        return node;
    }

    public boolean contains(Object item) {
        return ((search(item) == null) ? false : true);
    }

    public void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAt(int index, T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node newNode = new Node(item);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            int idx = 1;
            Node node = head;
            while (node != null) {
                if (idx == index) {
                    newNode.next = node.next;
                    node.next = newNode;
                    break;
                }
                idx++;
                node = node.next;
            }
        }
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
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        size++;
    }

    public void insert(T item) {
        insertEnd(item);
    }

    public void removeBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Singly linked list is empty");
        }
        head = head.next;
        size--;
    }

    public void removeAt(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node node = head;
            int idx = 1;
            while (node != null) {
                if (idx == index) {
                    node.next = node.next.next;
                    break;
                }
                node = node.next;
                idx++;
            }
        }
        size--;
    }

    public void removeEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Singly linked list is empty");
        }
        if (head.next == null) {
            head = null;
        } else {
            Node node = head;
            while (node.next.next != null) {
                node = node.next;
            }
            node.next = null;
        }
        size--;
    }

    public T getBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Singly linked list is empty");
        }
        return head.data;
    }

    public T getAt(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node node = head;
        int idx = 0;
        while (node != null) {
            if (idx == index) {
                break;
            }
            idx++;
            node = node.next;
        }
        return node.data;
    }

    public T getEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Singly linked list is empty");
        }
        Node node = head;
        while (node.next != null) {
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
        Node node = head;
        int idx = 0;
        while (node != null) {
            if (idx == index) {
                node.data = item;
                break;
            }
            node = node.next;
            idx++;
        }
    }

    public void reverse() {
        Node backward = null;
        Node current = head;
        Node forward = null;
        while (current != null) {
            forward = current.next;
            current.next = backward;
            backward = current;
            current = forward;
        }
        head = backward;
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
        Node node = head;
        while (node.next != null) {
            sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}