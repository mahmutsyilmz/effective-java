abstract class SuperClass {

    /**
     * override edilebilen methodlari constructor icerisinde cagirmak dogru bir kullanim degildir.
     * Cunku alt siniflardan gelen implementasyonlarin constructor cagirilirken hazir olmasi beklenemez.
     */
    public SuperClass() {
        System.out.println("SuperClass constructor");

        overridableMethod();
    }

    abstract void overridableMethod();
}
