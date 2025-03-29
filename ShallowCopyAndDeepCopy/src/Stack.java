import java.util.Objects;

public class Stack implements Cloneable {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public String stackName;

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public String getStackName() {
        return stackName;
    }

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            Object[] oldElements = elements;
            elements = new Object[2 * size + 1];
            System.arraycopy(oldElements, 0, elements, 0, size);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }





    public static void main(String[] args) {
        Stack stack1 = new Stack();
        String[] numbers = {"1", "2", "3", "4", "5"};

        for (String number : numbers) {
            stack1.push(number);
        }

        Stack copy = (Stack) stack1.clone(); //eğer clone metodu Stack sınıfında tanımlanmamış olsaydı bu satır hata receptive

        while (!stack1.isEmpty()) {
            System.out.println(stack1.pop());
        }

        System.out.println("Stack1 is empty: " + stack1.isEmpty());

        while (!copy.isEmpty()) {
            System.out.println(copy.pop());
        }

        System.out.println("Copy is empty: " + copy.isEmpty());




    }
}
