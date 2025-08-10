class LinkedDeque<T> {
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
    private Node front;
    private Node rear;
    private int size;
    LinkedDeque() {
        front = null;
        rear = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        if (front == null) {
            return true;
        } else {
            return false;
        }
    }

    void pushBack(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node newNode = new Node(item);
            if (rear == null) {
                front = newNode;
                rear = newNode;
            } else {
                newNode.prev = rear;
                rear.next = newNode;
                rear = newNode;
            }
            size = size + 1;
        }
    }

    void pushFront(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node newNode = new Node(item);
            if (front == null) {
                front = newNode;
                rear = newNode;
            } else {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size = size + 1;
        }
    }

    T popBack() {
        if (isEmpty()) {
            throw new IllegalStateException("LinkedDeque is empty");
        } else {
            Node temp = rear;
            T element = rear.data;
            rear = rear.prev;
            rear.next = null;
            temp.data = null;
            temp.prev = null;
            temp = null;
            size = size - 1;
            return element;
        }
    }

    T popFront() {
        if (isEmpty()) {
            throw new IllegalStateException("LinkedDeque is empty");
        } else {
            Node temp = front;
            T element = front.data;
            front = front.next;
            front.prev = null;
            temp.data = null;
            temp.next = null;
            temp = null;
            size = size - 1;
            return element;
        }
    }

    T back() {
        if (isEmpty()) {
            throw new IllegalStateException("LinkedDeque is empty");
        } else {
            return rear.data;
        }
    }

    T front() {
        if (isEmpty()) {
            throw new IllegalStateException("LinkedDeque is empty");
        } else {
            return front.data;
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            Node iterator = front;
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