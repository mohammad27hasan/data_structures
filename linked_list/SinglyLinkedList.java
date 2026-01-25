/*
* Data structure: Linked list
* Abstract data type: Singly linked list
* Version: 1.0.8
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

    private int size;
    private Node head;

    public SinglyLinkedList() {
        size = 0;
        head = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    private Node search(T key) {
        Node node = head;
        while ((node != null) && !key.equals(node.data)) {
            node = node.next;
        }
        return node;
    }

    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        return (search(element) != null);
    }

    public void insertBeginning(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAt(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node newNode = new Node(element);
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

    public void insertEnd(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        Node newNode = new Node(element);
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

    public void insert(T element) {
        insertEnd(element);
    }

    public void removeBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
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
            throw new java.util.NoSuchElementException("Size: " + size);
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
            throw new java.util.NoSuchElementException("Size: " + size);
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
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        Node node = head;
        while (node.next != null) {
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
        Node node = head;
        int idx = 0;
        while (node != null) {
            if (idx == index) {
                node.data = element;
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