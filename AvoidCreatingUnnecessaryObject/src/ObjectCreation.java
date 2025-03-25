import java.util.ArrayList;
import java.util.List;

public class ObjectCreation {

    private static long sumPrimitive (){
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        return sum;
    }

    private static Long sumWrapper (){
        Long sum = 0L;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {

        String word = new String("Hello");

        // This is the same as the above line
        String word2 = "Hello";
        String word3 = "Hello";

        System.out.println(word == word2); // false
        System.out.println(word2 == word3); // true

        long start = System.currentTimeMillis();
        sumPrimitive();
        System.out.println("Time taken for primitive: " + (System.currentTimeMillis() - start) + "ms");
        // 2 ms
        sumWrapper();
        System.out.println("Time taken for wrapper: " + (System.currentTimeMillis() - start) + "ms");
        // 16 ms

        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            integers.add(i); // i -> Integer.valueOf(i);
        }

        List<Integer> integers2 = new ArrayList<>();
        int sum2 = 0;
        for (Integer i = 0; i < 1000000; i++) {

            if (i % 2 == 0) {
                sum2 += i; //i.intValue()
            }
        }


    }
}
