
public class Main {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 " + "Hashcode of Lazy: " + LazyCache.getInstance().hashCode());
                System.out.println("Thread 1 " + "Hashcode of Eager: " + EagerCache.getInstance().hashCode());
                System.out.println("Thread 1 " + "Hashcode of Enum: " + CacheSingleton.INSTANCE.hashCode());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 " + "Hashcode of Lazy: " + LazyCache.getInstance().hashCode());
                System.out.println("Thread 2 " + "Hashcode of Eager: " + EagerCache.getInstance().hashCode());
                System.out.println("Thread 2 " + "Hashcode of Enum: " + CacheSingleton.INSTANCE.hashCode());
            }
        }).start();

        

        /*
        OUTPUT:
        Thread 2 Hashcode of Lazy: 2135059911
        Thread 1 Hashcode of Lazy: 1700962600
        Thread 2 Hashcode of Eager: 1270588713
        Thread 1 Hashcode of Eager: 1270588713
        Thread 2 Hashcode of Enum: 1072479738
        Thread 1 Hashcode of Enum: 1072479738
         */
    }
    }
