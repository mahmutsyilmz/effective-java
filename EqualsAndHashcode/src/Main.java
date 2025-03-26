import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //integer-type
        System.out.println(10 == 20); // false
        //char-type
        System.out.println('A' == 'B'); // false
        //char and double type
        System.out.println('A' == 65.0); // true
        //boolean-type
        System.out.println(true == true); // true
        //String-type
        System.out.println("Hello" == "Hello"); // true
        //String-type
        System.out.println("Hello" == new String("Hello")); // false

        System.out.println((int) 'A');  // 65
        System.out.println('A' + 1); // 66

        Class class1 = new Class(1,2);
        Class class2 = new Class(1,2);

        System.out.println(class1 == class2); // false
        System.out.println(class1.equals(class2)); // false
        System.out.println(class1.hashCode() == class2.hashCode()); // false
        System.out.println(class1.equals(class1)); // true

        ClassWithOverride classWithOverride1 = new ClassWithOverride(1,2);
        ClassWithOverride classWithOverride2 = new ClassWithOverride(1,2);

        System.out.println(classWithOverride1 == classWithOverride2); // false
        System.out.println(classWithOverride1.equals(classWithOverride2)); // true

        //hashcode override etmezsek
        System.out.println(classWithOverride1.hashCode() == classWithOverride2.hashCode()); // false

        Map<ClassWithOverride, String> map = new HashMap<>();
        map.put(new ClassWithOverride(1,2),"Hello");
        System.out.println(map.get(new ClassWithOverride(1,2))); // null gelir çünkü hashcode'ları farklı



        //hashcode override edersek
        System.out.println(classWithOverride1.hashCode() == classWithOverride2.hashCode()); // true
        map.put(new ClassWithOverride(1,2),"Hello");
        System.out.println(map.get(new ClassWithOverride(1,2))); // Hello gelir çünkü hashcode'ları aynı




    }
}
