import java.util.HashMap;
import java.util.Map;

public class FixWithComposition {
    /**
     * Bu sefer composition kullanarak HashMap sınıfını kullanacağız.
     * Bu sayede sadece ihtiyacımız olan methodları kullanmış olacağız.
     * Composition, inheritance'a göre daha esnek bir yapıdır.
     */
    private static Map<String,String>  map = new HashMap();

    public static void main(String[] args) {
        map.put("key", "value");
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
