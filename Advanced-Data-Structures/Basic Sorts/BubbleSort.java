import java.util.*;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {98, 34, 27 , 23, 12, 47, 66};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
