public class RecursiveAsterick {

    public static void main(String[] args) {
        printAstericks(4);
        pattern(840);
    }

    public static void printAstericks( int count) {
        if (count <= 0 ) return;
        System.out.print("*");
        printAstericks(--count);
        System.out.print("!");
        return;
    }

    public static void pattern(int n) {
        if (n > 4242) return; // base case
        // recursive case
        int x = n;
        System.out.println(x);
        pattern(2 * n);
        System.out.println(n);
        return;


    }
}
