public class Factorial {

    public static void main(String[] args) {
        int n = 5;
        int result = factorial(n);
        System.out.println("The factorial of " + n + " is " + result);
    }

    public static int factorial(int n) {
        if (n == 0) return 1;
        return factorial(n - 1) * n;
        /*
          Base case: n == 0
          Recursive case:
           Let n = 5,

           return 5 * factorial(4)
           -> return 4 * factorial(3)
               -> return 3 * factorial(2)
                   -> return 2 * factorial(1)
                       -> return 1 * factorial(0)
                           -> return 1
         */
    }
}
