public class Main {
    public static void main(String[] args) {
        Engine engine1 = new Engine("Mercedes");
        Engine engine2 = new Engine("Ferrari");
        CarWithIoC carWithIoC = new CarWithIoC(engine1);

        CarNoIoC carNoIoC = new CarNoIoC();

        carWithIoC.start();
        carNoIoC.start();
    }
}
