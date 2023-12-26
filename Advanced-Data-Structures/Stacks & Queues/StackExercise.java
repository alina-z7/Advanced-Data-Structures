import java.util.Stack;
public class StackExercise {
    public static void main(String[] args){
        //create Stack using built in Java class
        Stack<Integer> stack = new Stack<>();

        //push elements into stack
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        //call methods
        reverseOrder(stack);
        insertElementAtIndex(stack, 2, 6);
        smallestIdxOfStack(stack, 3);
        System.out.println(isPalindrome("racecar"));


    }

    public static void reverseOrder(Stack<Integer> stack) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }

    public static void insertElementAtIndex(Stack<Integer> stack, int idx, int val) {
        stack.add(idx, val);
        System.out.println(stack);
    }

    public static int smallestIdxOfStack(Stack<Integer> stack, int val) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) == val) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isPalindrome(String message) {
        Stack<String> stack = new Stack<>();
        StringBuilder reverse = new StringBuilder();
        for (int i = message.length() - 1; i >= 0; i--) {
            stack.push(String.valueOf(message.charAt(i)));
        }
        for (int j = 0; j < stack.size(); j++) {
            reverse.append(stack.get(j));
        }
        return reverse.equals(message);
    }

}
