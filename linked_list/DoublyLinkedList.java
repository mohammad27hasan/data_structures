/*
* Data_structrue: DoublyLinkedList
* Version: 1.0.2
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
    private Node head;
    private Node tail;
    private int size;
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return ((head == null) || (tail == null));
    }

    private Node search(Object item) {
        Node node = head;
        while ((node != null) && !node.data.equals(item)) {
            node = node.next;
        }
        return node;
    }

    public boolean contains(Object item) {
        Node node = search(item);
        if (node == null) {
            return false;
        }
        return true;
    }

    public void insertBeginning(T item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
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
            tail = newNode;
        } else {
            newNode.prev = tail;
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
        } else if (index == size) {
            insertEnd(item);
        } else {
            Node newNode = new Node(item);
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

    public void insert(T item) {
        insertEnd(item);
    }

    public void removeBeginning() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Doubly linked list is empty");
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
    }

    public void removeEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Doubly linked list is empty");
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
            throw new java.util.NoSuchElementException("Doubly linked list is empty");
        }
        return head.data;
    }

    public T getEnd() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Doubly linked list is empty");
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
        while (node != null) {
            sb.append(node.data);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}