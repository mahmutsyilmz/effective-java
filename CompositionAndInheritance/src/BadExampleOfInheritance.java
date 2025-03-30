import java.util.HashMap;

public class BadExampleOfInheritance extends HashMap<String, String> {

    public static void main(String[] args) {

        /**
         * Bu kötü bir kalıtım örneğidir, çünkü sadece put methodunu kullanmak için
         * HashMap sınıfını inherit ettik.
         * HashMap sınıfındaki bütün methodları, bu sınıfa da eklemiş olduk.
         * Ancak biz sadece put methodunu kullanmak istiyorduk.
         */

        BadExampleOfInheritance badExampleOfInheritance = new BadExampleOfInheritance();
        badExampleOfInheritance.put("key", "value");
        badExampleOfInheritance.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
