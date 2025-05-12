class DynamicArrayStack<T> {
    private int capacity;
    private int top;
    private T[] array;
    private int size;
    DynamicArrayStack() {
        this(14);
    }

    @SuppressWarnings("unchecked")
    DynamicArrayStack(int initialCapacity) {
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
        String s = "[";
        for (int i = 0; i < size; i++) {
            s = s.concat(String.valueOf(array[i]));
            if (i < size - 1) {
                s = s.concat(", ");
            }
        }
        s = s.concat("]");
        return s;
    }

    boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isFull() {
        if (size == capacity) {
            return true;
        } else {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    void push(T item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        } else if (isFull()) {
            capacity = capacity * 2;
            T[] tempArray = (T[])new Object[capacity];
            for (int i = 0; i < size; i++) {
                tempArray[i] = array[i];
            }
            top = top + 1;
            tempArray[top] = item;
            size = size + 1;
            array = tempArray;
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