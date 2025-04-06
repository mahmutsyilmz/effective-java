public class StackTechOne<E>{

    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public void finalize() {
        System.out.println("Stack is being garbage collected");
    }

    @SuppressWarnings("unchecked")
    public StackTechOne() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public void push(E element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E element = elements[--size];
        elements[size] = null; // Prevent memory leak
        return element;
    }

    public E peek() {
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
        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
