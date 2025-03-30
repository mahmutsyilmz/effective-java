public class Cat extends Animal {

    public Cat(String name, int age, String color) {
        super(name, age, color);
    }

    @Override
    public void sound() {
        System.out.println("Cat is making sound...");
    }

    public void meow() {
        System.out.println("Cat is meowing...");
    }
}
