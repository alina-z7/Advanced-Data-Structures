import java.util.ArrayList;

public class HashTable {

    class HashNode {
        HashNode next;
        int val;
        String key;
        public HashNode(String key, int val) {
            this.key = key;
            this.val = val;
            next = null;
        }
    }
    private int size;
    private HashNode[] dataMap;

    public HashTable() {
        size = 7;
        dataMap = new HashNode[size];
    }

    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int asciiVal = keyChars[i];
            hash = (hash + asciiVal * 23) % dataMap.length;
        }
        return hash;
    }

    public void set(String key, int val) {
        int idx = hash(key);
        HashNode newNode = new HashNode(key, val);
        if (dataMap[idx] == null) {
            dataMap[idx] = newNode;
        } else {
           HashNode curr = dataMap[idx];
           while (curr.next != null) {
               curr = curr.next;
           }
           curr.next = newNode;
        }
    }

    public int get(String key) {
        int idx = hash(key);
        HashNode curr = dataMap[idx];
        while (curr != null) {
            if (curr.key == key) return curr.val;
            curr = curr.next;
        }
        return -1;
    }

    public ArrayList<String> keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (int i = 0; i < dataMap.length; i++) {
            HashNode curr = dataMap[i];
            while (curr != null) {
                allKeys.add(curr.key);
                curr = curr.next;
            }
        }
        return allKeys;
    }

    public void printHashTable() {
        System.out.println("Hash Table:");
        System.out.println("---------------");

        for (int i = 0; i < dataMap.length; i++) {
            System.out.print("| " + i + " | ");

            HashNode curr = dataMap[i];
            boolean firstEntry = true;

            while (curr != null) {
                if (!firstEntry) {
                    System.out.print(" -> ");
                }

                System.out.print("{" + curr.key + "=" + curr.val + "}");
                firstEntry = false;
                curr = curr.next;
            }

            System.out.println(" |");
        }

        System.out.println("---------------");
    }

    public static void main(String[] args) {
        HashTable newHT = new HashTable();
        newHT.printHashTable();
        System.out.println();

        newHT.set("nails", 100);
        newHT.set("tile", 50);
        newHT.set("lumber", 80);
        newHT.set("bolts", 40);
        newHT.set("paint", 20);
        newHT.printHashTable();
        System.out.println();

        System.out.println(newHT.get("lumber"));
        System.out.println(newHT.get("bolts"));
        System.out.println();

        System.out.println(newHT.keys());
    }


}


