public class Book {
    private String name;
    private String author;
    private int page;

    public Book(String name, String author, int page) {
        this.name = name;
        this.author = author;
        this.page = page;
    }

    public void showInfo() {
        System.out.println("Name: " + name + " Author: " + author + " Page: " + page);
    }
}
