public class SubClass extends SuperClass{

    final int value;

    public SubClass(int value) {
        System.out.println("SubClass constructor");
        this.value = value;
    }


    @Override
    void overridableMethod() {
        System.out.println("SubClass overridableMethod");
    }
}
