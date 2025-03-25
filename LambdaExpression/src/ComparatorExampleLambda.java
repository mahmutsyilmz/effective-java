import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorExampleLambda {
    public static void main(String[] args) {


        List<String> names = new ArrayList<>();
        names.add("Steve");
        names.add("Tim");
        names.add("Lucy");
        names.add("Pat");

        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
        System.out.print(names);

        Collections.sort(names, (o1, o2) -> o2.compareTo(o1));
        System.out.print(names);

        Collections.sort(names, (o1, o2) -> o1.length() - o2.length());
        System.out.print(names);

    }
}
