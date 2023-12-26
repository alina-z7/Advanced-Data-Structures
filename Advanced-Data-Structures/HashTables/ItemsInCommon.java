import java.util.HashMap;

public class ItemsInCommon {

    public static boolean itemInCommon1(int[] arr1, int[] arr2) {
        for (int i : arr1) {
            for (int j : arr2) {
                if (i == j) return true;
            }
        }
        return false;
    }

    public static boolean itemInCommon2(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> commonMap = new HashMap<>();
        for (int i : arr1) {
            commonMap.put(i, true);
        }
        for (int j : arr2) {
            if (commonMap.containsKey(j)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Using for loops: " + itemInCommon1(new int[] {1, 3, 5}, new int[] {2, 4, 6}));
        System.out.println("Using HashMap: " + itemInCommon2(new int[] {1, 3, 5}, new int[] {2, 4, 5}));

    }
}
