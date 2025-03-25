public class RunnableExampleLambda {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Lambda Runnable");
        new Thread(r).start();

        //It may be shorter
        new Thread(() -> System.out.println("Lambda Runnable")).start();
    }

}
