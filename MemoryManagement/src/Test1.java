import java.util.ArrayList;

public class Test1 {
    public static final ArrayList<Double> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        MemoryMonitor monitor = new MemoryMonitor();
        monitor.start();

        Test1 test = new Test1();
        test.fillArray();
        System.out.println("Array is filled");
        System.gc();
        Thread.sleep(1000); // Biraz bekle

        test.fillArray();
        System.out.println("Array is filled again");
        System.gc();
        Thread.sleep(1000); // Biraz bekle

        monitor.interrupt(); // Monitor thread'ini sonlandır

        /*
        Kullanılan Bellek: 4 MB
        Array is filled
        Kullanılan Bellek: 36 MB
        Array is filled again
        Kullanılan Bellek: 62 MB
         */
    }


    private void fillArray() {
        for (int i = 0; i < 1000000; i++) {
            list.add(Math.random());
        }

    }
}