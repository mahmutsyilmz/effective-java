import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Object[] objectArray = new Long[1];
        objectArray[0] = true; // Bu kullanım runtime'da ClassCastException hatası verecektir

        List<Object> o1 = new ArrayList<Long>();
        o1.add("Hello"); // Bu kullanım compile-time hatası verecektir, yani biz bu hatayı kod yazarken göreceğiz

        /**
         * Dizilerde Object[] -> Long[] süper sınıfıdır.
         * Bu yüzden Object[] dizisine Long[] dizisi atanabilir.
         */


    }
}
