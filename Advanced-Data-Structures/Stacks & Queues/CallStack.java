public class CallStack {

    public static void main(String[] args) {
        methodOne();
    }

    public static void methodOne() {
        methodTwo();
        System.out.println("1");
    }

    public static void methodTwo() {
        methodThree();
        System.out.println("2");
    }

    public static void methodThree() {
        System.out.println("3");
    }
}
