public class Test1 {
    public static void main(String[] args) {
        //shallow copy

        Stack stack1 = new Stack();
        Stack stack2 = stack1;

        //referansları aynı olduğu için stack1 ve stack2 aynı nesneyi gösterirler
        System.out.println(stack1 == stack2); //true

        //deep copy
        Stack stc = new Stack();
        stc.setStackName("Stack1");
        System.out.println(stc.getStackName()); //Stack1

        Stack stc2 = new Stack();
        stc2.setStackName(stc.getStackName()); //Stack1
        System.out.println(stc2.getStackName()); //Stack1

    }
}
