public class Fruit implements Comparable<Fruit> {


    private String name;
    private String color;

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return name + " " + color;
    }

    @Override
    public int compareTo(Fruit o) {
        return this.name.compareTo(o.name);
    }


}
