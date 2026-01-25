/*
* Data structure: Linked list
* Abstract data type: Doubly linked list
* Version: 1.0.5
* Author: Mohammad Hasan
*/
package ds.linked;

public class DoublyLinkedList<T> {
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

    private int size;
    private Node head;
    private Node tail;
    
    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return ((head == null) || (tail == null));
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
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
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
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
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
        } else if (index == size) {
            insertEnd(element);
        } else {
            Node newNode = new Node(element);
            Node node = head.next;
            int idx = 1;
            while (node != null) {
                if (idx == index) {
                    newNode.next = node;
                    newNode.prev = node.prev;
                    node.prev.next = newNode;
                    node.prev = newNode;
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
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
    }

    public void removeEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
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
            while (node != null) {
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
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return head.data;
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
            idx++;
            node = node.next;
        }
    }

    public void reverse() {
        Node backward = null;
        Node current = head;
        Node forward = null;
        while (current != null) {
            forward = current.next;
            current.next = backward;
            current.prev = forward;
            backward = current;
            current = forward;
        }
        tail = head;
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
        while (node != tail) {
            sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append(node.data + "]");
        return sb.toString();
    }
}