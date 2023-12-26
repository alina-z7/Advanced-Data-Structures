import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

class LUCQueue {

    Queue<Integer> queue;
    public LUCQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        queue.remove();
    }

    public void dequeue(int val) {
        queue.add(val);
    }

}
public class QueueExercise{
    public static void main(String[] args){
        //create queue using built in Java interface with linked list
        Queue<Integer> queue = new LinkedList<>();

        //offer or push items into queue

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue);

        //call methods
        reverseQueue(queue);
        firstKItems(queue, 3);
        pollOrNot(queue);
    }

    public static void reverseQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        /**
         * Recursive Method:
         *
         * if (q.size == 0)
         * return 0;
         * else
         * val = q.dequeue();
         * reverseQueue(queue);
         * q.enqueue(val);
         */
    }

    public static void firstKItems(Queue<Integer> queue, int k) {
        /**
        solve(queue, k);
        int s = queue.size() - k;
        while (s > 0) {
            int val = queue.poll();
            queue
        }
         private void solve(LUCQueue queue, int k) {
         if (k == 0)  return;
         int val = q.poll();
         solve(queue, k-1);
         }
         */
    }

    public static boolean pollOrNot(Queue<Integer> queue) {
        return false;
    }

}
