public class Virus {
    public String virusOnNonSafeCode (){
        NonSafeCode.DEALED_BRANDS[0] = "Virus";
        return NonSafeCode.DEALED_BRANDS[0];
    }

    // we cant change the value of DEALED_BRANDS in SafeCode class because it is final aand private
//    public String virusOnSafeCode (){
//        SafeCode.DEALED_BRANDS[0] = "Virus";
//        return SafeCode.DEALED_BRANDS[0];
//    }

    // we can change the value of DEALED_BRANDS_LIST in SafeCode class because it is public
    public String virusOnSafeCodeAsList(){
        SafeCode.DEALED_BRANDS_LIST.set(0, "Virus");
        return SafeCode.DEALED_BRANDS_LIST.get(0);
    }


}
