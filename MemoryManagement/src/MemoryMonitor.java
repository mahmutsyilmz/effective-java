public class MemoryMonitor extends Thread {
    @Override
    public void run() {
        Runtime runtime = Runtime.getRuntime();
        while (!Thread.currentThread().isInterrupted()) {
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            System.out.println("Kullanılan Bellek: " + (usedMemory / (1024 * 1024)) + " MB");
            try {
                Thread.sleep(1000); // 1 saniye aralıklarla ölçüm
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
