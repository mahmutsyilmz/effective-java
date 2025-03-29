public class Main {
    public static void main(String[] args) {

        Class1 obj = new Class1();
//        obj.message(); // This will not work as message() is private
//        obj.name(); // This will not work as name is private
//        obj.getName(); // This will work as getName() is public
//        obj.surname(); // This will not work as surname is protected

        NonSafeCode nonSafeCode = new NonSafeCode();
        nonSafeCode.getBrands();
        // This will work as DEALED_BRANDS is public
        // and can be accessed from any class
        Virus virus = new Virus();
        System.out.println(virus.virusOnNonSafeCode());
        // "Virus" will be printed as DEALED_BRANDS is public

        SafeCode safeCode = new SafeCode();
        safeCode.getBrands();

        Virus virus1 = new Virus();
        System.out.println(virus1.virusOnSafeCodeAsList());
        // this will throw an exception as DEALED_BRANDS_LIST is unmodifiable




    }
}
