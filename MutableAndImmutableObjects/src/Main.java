import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.BitSet;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        String name = new String("John");
        name.toLowerCase();
        System.out.println(name.toLowerCase()); // john
        System.out.println(name); // John
        // bir değişiklik olmadı çünkü String immutable bir sınıftır.

        //mutable
        Date date = new Date();
        System.out.println(date); // Wed Mar 31 14:47:00 EEST 2021
        date.setDate(7);
        System.out.println(date); // Wed Apr 07 14:47:00 EEST 2021
        // Date sınıfı mutable bir sınıftır, bu yüzden değişiklik yapabiliyoruz.

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); //2025-03-30
        localDate.plusDays(2);
        System.out.println(localDate); //2025-03-30
        // LocalDate sınıfı immutable bir sınıftır, bu yüzden değişiklik yapamıyoruz.
        // ancak eğer şöyle yapsaydık:
        localDate = localDate.plusDays(2);
        System.out.println(localDate); //2025-04-01
        // bu şekilde yeni bir nesne oluşturmuş olduk ve değişiklik yaptık.

        BigInteger bigInteger = new BigInteger("1234567890");
        System.out.println(bigInteger); // 1234567890
        bigInteger.add(BigInteger.ONE);
        System.out.println(bigInteger); // 1234567890

        BitSet bitSet = new BitSet();
        System.out.println(bitSet); // {}
        bitSet.set(0);
        System.out.println(bitSet); // {0}
        // BitSet sınıfı mutable bir sınıftır, bu yüzden değişiklik yapabiliyoruz.

        StringBuilder stringBuilder = new StringBuilder("John");
        System.out.println(stringBuilder); // John
        stringBuilder.append(" Doe");
        System.out.println(stringBuilder); // John Doe
        // StringBuilder sınıfı mutable bir sınıftır, bu yüzden değişiklik yapabiliyoruz.






    }
}
