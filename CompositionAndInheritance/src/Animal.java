public class Animal {
    private String name;
    private int age;
    private String color;

    public Animal(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public void eat() {
        System.out.println("Animal is eating...");
    }

    public void sleep() {
        System.out.println("Animal is sleeping...");
    }

    public void move() {
        System.out.println("Animal is moving...");
    }

    public void sound() {
        System.out.println("Animal is making sound...");
    }

    public void showInfo() {
        System.out.println("Name: " + name + " Age: " + age + " Color: " + color);
    }
}
