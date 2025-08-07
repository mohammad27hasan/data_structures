class CircularDeque<T> {
    private int front;
    private int rear;
    private int capacity;
    private int size;
    private T[] buffer;

    @SuppressWarnings("unchecked")
    CircularDeque(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Invalid CircularDeque capacity " + capacity);
        } else {
            front = -1;
            rear = -1;
            this.capacity = capacity;
            size = 0;
            buffer = (T[])(new Object[capacity]);
        }
    }

    int capacity() {
        return capacity;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        if (front == -1) {
            return true;
        } else {
            return false;
        }
    }

    boolean isFull() {
        if (size == capacity) {
            return true;
        } else {
            return false;
        }
    }

    void pushBack(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (isFull()) {
            throw new IllegalStateException("CircularDeque is full");
        } else {
            rear = (rear + 1) % capacity;
            buffer[rear] = item;
            if (front == -1) {
                front = rear;
            }
            size = size + 1;
        }
    }

    void pushFront(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (isFull()) {
            throw new IllegalStateException("CircularDeque is full");
        } else {
            if (front == -1) {
                front = 0;
                rear = 0;
            } else if (front == 0) {
                front = capacity - 1;
            } else {
                front = front - 1;
            }
            buffer[front] = item;
            size = size + 1;
        }
    }

    T popBack() {
        if (isEmpty()) {
            throw new IllegalStateException("CircularDeque is empty");
        } else {
            T element = buffer[rear];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (rear == 0) {
                rear = capacity - 1;
            } else {
                rear = rear - 1;
            }
            size = size - 1;
            return element;
        }
    }

    T popFront() {
        if (isEmpty()) {
            throw new IllegalStateException("CircularDeque is empty");
        } else {
            T element = buffer[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % capacity;
            }
            size = size - 1;
            return element;
        }
    }

    T front() {
        if (isEmpty()) {
            throw new IllegalStateException("CircularDeque is empty");
        } else {
            return buffer[front];
        }
    }

    T back() {
        if (isEmpty()) {
            throw new IllegalStateException("CircularDeque is empty");
        } else {
            return buffer[rear];
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            int i = front;
            for (int j = 0; j < size; j++) {
                sb.append(buffer[i]);
                if (j < size - 1) {
                    sb.append(", ");
                }
                i = (i + 1) % capacity;
            }
            sb.append("]");
            return sb.toString();
        }
    }
} 