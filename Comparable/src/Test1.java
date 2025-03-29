import java.util.ArrayList;
import java.util.Collections;

public class Test1  {
    public static void main(String[] args) {
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit("Apple", "Red"));
        fruits.add(new Fruit("Banana", "Yellow"));
        fruits.add(new Fruit("Orange", "Orange"));
        fruits.add(new Fruit("Grape", "Green"));
        fruits.add(new Fruit("Plum", "Purple"));


        Collections.sort(fruits);
        System.out.println("*İsimlerine göre sıralama*");
        //sıralama 1
        for (Fruit fruit : fruits) {
            System.out.println(fruit.toString());
        }
        System.out.println("************");
        System.out.println("*Renklerine göre sıralama*");
        //sıralama 2
        Collections.sort(fruits, new FruitComparator());
        for (Fruit fruit : fruits) {
            System.out.println(fruit.toString());
        }

    }
}
