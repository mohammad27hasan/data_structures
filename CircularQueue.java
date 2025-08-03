class CircularQueue<T> {
    private int front;
    private int rear;
    private int capacity;
    private int size;
    private T[] buffer;

    @SuppressWarnings("unchecked")
    CircularQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Invalid CircularQueue capacity " + capacity);
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

    void enqueue(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else if (isFull()) {
            throw new IllegalStateException("CircularQueue is full");
        } else {
            rear = (rear +  1) % capacity;
            buffer[rear] = item;
            size = size + 1;
            if (front == -1) {
                front = rear;
            }
        }
    }

    T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("CircularQueue is empty");
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
            throw new IllegalStateException("CircularQueue is empty");
        } else {
            return buffer[front];
        }
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            int i = front;
            StringBuilder sb = new StringBuilder("[");
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