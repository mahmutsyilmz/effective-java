import java.util.Collections;
import java.util.List;

public class SafeCode {
    private static final String[] DEALED_BRANDS = new String[]{"Apple", "Samsung", "Huawei", "Xiaomi"};
    //or
    public static final List<String> DEALED_BRANDS_LIST = Collections.unmodifiableList(List.of("Apple", "Samsung", "Huawei", "Xiaomi"));

    public static void getBrands() {
        for (String brand : DEALED_BRANDS) {
            System.out.print(brand + " ");
        }
    }


}
