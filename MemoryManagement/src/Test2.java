import java.util.ArrayList;

public class Test2 {
    // Bellek kullanımını izlemek istediğimiz liste
    private final ArrayList<Double> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // Programın bitmeden sürekli çalışması için sonsuz döngü
        Test2 test = new Test2();

        while (true) {
            // Listeyi doldur
            test.fillArray();
            System.out.println("Array is filled. Size: " + test.list.size());

            // Bir süre bekle (JConsole'dan bu arada bağlanıp izleyebilirsiniz)
            Thread.sleep(10000);

            // Listeyi temizle
            test.clearArray();
            System.out.println("Array is cleared. Size: " + test.list.size());

            // Tekrar bekle, döngü başa dönecek
            Thread.sleep(5000);
        }
    }

    /**
     * Listeyi belli sayıda rastgele sayı ile doldurur.
     */
    private void fillArray() {
        for (int i = 0; i < 5_000_000; i++) {
            list.add(Math.random());
        }
    }

    /**
     * Listeyi tamamen temizler.
     */
    private void clearArray() {
        list.clear();
    }
}
