import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Inheritance
        Cat cat = new Cat("Tom", 3, "White");
        cat.showInfo(); // Name: Tom Age: 3 Color: White
        cat.sound(); // Cat is making sound...
        cat.eat(); // Animal is eating...

        //Composition
        Library library = new Library(List.of(
                new Book("Book1", "Author1", 100),
                new Book("Book2", "Author2", 200),
                new Book("Book3", "Author3", 300)
        ));

        library.showBooks();

    }
}
