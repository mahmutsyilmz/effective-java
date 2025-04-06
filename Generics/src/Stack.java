public class Stack {

    public Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public void finalize() {
        System.out.println("Stack is being garbage collected");
    }

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Object element = elements[--size];
        elements[size] = null; // Prevent memory leak
        return element;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
