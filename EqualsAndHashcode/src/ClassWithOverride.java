public class ClassWithOverride {
    private int a;
    private int b;

    public ClassWithOverride(int a,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ClassWithOverride)) return false;
        ClassWithOverride classWithOverride = (ClassWithOverride) obj;
        return classWithOverride.a == this.a && classWithOverride.b == this.b;

    }


    /***
     * bir equals methodunu override ettiğimzde, bu method 5 özelliğe sahip olmalı
     * reflexive -> x.equals(x) true olmalı
     * symmetric -> x.equals(y) true ise y.equals(x) true olmalı
     * transitive -> x.equals(y) true ise y.equals(z) true ise x.equals(z) true olmalı
     * consistent -> x.equals(y) true ise her zaman true olmalı
     * null -> x.equals(null) false olmalı
     */

    //eğer ki equals methodunu override ettiyseniz, hashCode methodunu da override etmelisiniz
    @Override
    public int hashCode() {
        int result = Integer.hashCode(a);
        result = 31 * result + Integer.hashCode(b);
        return result;
    }
}
