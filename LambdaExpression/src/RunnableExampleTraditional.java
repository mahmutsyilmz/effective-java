public class RunnableExampleTraditional {
    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional Runnable");
            }
        };
        new Thread(r).start();


    }
}
