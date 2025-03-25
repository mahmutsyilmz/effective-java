import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class StreamLambdaExample {
    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "grape", "orange", "watermelon");

        List<String> longFruitsUpperCase = fruits.stream()
                .filter(fruit -> fruit.length() > 5)
                .map(fruit -> fruit.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(longFruitsUpperCase);


    }
}