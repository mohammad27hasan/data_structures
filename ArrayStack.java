class ArrayStack<T> {
    private int capacity;
    private int top;
    private T[] array;
    private int size;
    ArrayStack() {
        this(14);
    }

    @SuppressWarnings("unchecked")
    ArrayStack(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Invalid Stack Capacity: " + initialCapacity);
        } else {
            array = (T[])new Object[initialCapacity];
            capacity = initialCapacity;
            top = -1;
            size = 0;
        }
    }

    int capacity() {
        return capacity;
    }

    int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    boolean isEmpty() {
        if (top == -1) {
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
    
    void push(T item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else if (isFull()) {
            throw new RuntimeException("Stack Overflow");
        } else {
            top = top + 1;
            array[top] = item;
            size = size + 1;
        }
    }

    T pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        } else {
            T item = array[top];
            top = top - 1;
            size = size - 1;
            return item;
        }
    }

    T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        } else {
            return array[top];
        }
    }
}