import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        /**
         * PECS - Producer Extends Consumer Super
         */

        /**
         * The following code will not compile because the type of the list is
         * List<Object> and the type of the object being added is String.
         * This is a compile-time error.
         */
        List<Object> objects1 = new ArrayList<String>();

        List<? extends A> list = new ArrayList<B>();

        List<? super B> list2 = new ArrayList<A>();

        list.add(new A()); // This will not compile because list is a producer of A

        list2.add(new B()); // This will compile because list2 is a consumer of B

        /**
         * When we want to write on a list we use super
         * When we want to read from a list we use extends
         */

        //read
        List<? extends A> list3 = new ArrayList<B>();
        for (A a : list3) {
            System.out.println(a);
        }

        //write
        List<? super B> list4 = new ArrayList<A>();
        list4.add(new B());
        list4.add(new C());
        list4.add(new D());




    }
}
