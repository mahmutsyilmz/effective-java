public class NonSafeCode {

    public static final String[] DEALED_BRANDS = new String[]{"Apple", "Samsung", "Huawei", "Xiaomi"};

    public static void getBrands() {
        for (String brand : DEALED_BRANDS) {
            System.out.print(brand + " ");
        }
    }
}
