import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Raw {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        unsafeAdd(list, 1);

        String s = list.get(0);
        System.out.println(s);
        // bu kod compile olur ama çalıştırıldığında hata verir
        // Exception in thread "main" java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String




    }

    private static void unsafeAdd(List list, Object o){
        list.add(o);
    }
}
