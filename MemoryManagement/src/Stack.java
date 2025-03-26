public class Stack {

    public Object[] stackArray;
    public int size = 0;
    private static final int DEFAULT_CAPACITY = 16;

    public void finalize(){
        System.out.println("Object is garbage collected");
    }

    public Stack() {
        stackArray = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object obj) {
        if (size == stackArray.length) {
            ensureCapacity();
        }
        stackArray[size++] = obj;
    }

    public Object pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[--size];
        /*
        bu kullanımda stackArray[size] değeri hala bellekte tutuluyor.
        bu sadece size değerini azaltıyor.
        en üstteki elemanı sanki yokmuş gibi davranıyor.
         */
    }

    public Object popWell() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        Object obj = stackArray[--size];
        stackArray[size] = null;
        return obj;
        /*
        burada ise stackArray[size] değerini null yapıyoruz.
        garbage collector bu değeri bellekten temizleyebilir.
         */
    }

    private void ensureCapacity() {
        int newSize = stackArray.length * 2;
        stackArray = java.util.Arrays.copyOf(stackArray, newSize);
    }

}
