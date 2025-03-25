public class GarbaceCollection {

    public void finalize(){
        System.out.println("Object is garbage collected");
    }

    public static void main(String[] args) {
        GarbaceCollection a = new GarbaceCollection();
        GarbaceCollection b = new GarbaceCollection();
        GarbaceCollection c = new GarbaceCollection();

        //garbage collector is called 2 times because a null and b is assigned to c, that means
        //b and c are pointing same object in heap memory

        a = null;
        b = c;

        System.gc();
    }
}
