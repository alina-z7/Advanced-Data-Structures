import java.util.*;

public class Heap {

    class HeapNode {
        int data;

        public HeapNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public String toString() {
            return "[" + data + "]";
        }
    }

    List<HeapNode> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public List<HeapNode> getHeap() {
        return new ArrayList<>(heap);
    }

    private int leftChild(int idx) {
        return 2 * idx + 1;
    }

    private int rightChild(int idx) {
        return 2 * idx + 2;
    }

    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    private void swap(int idx1, int idx2) {
        HeapNode temp = heap.get(idx1);
        heap.set(idx1, heap.get(idx2));
        heap.set(idx2, temp);
    }

    public void insert(int val) {
        HeapNode newNode = new HeapNode(val);
        heap.add(newNode);
        int curr = heap.size() - 1;
        while (curr > 0 && heap.get(curr).getData() > heap.get(parent(curr)).getData()) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    public HeapNode remove() {
        if (heap.size() == 0) {
            return null;
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        HeapNode maxVal = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        sinkDown(0);
        return maxVal;
    }

    public void sinkDown(int idx) {
        int maxIdx = idx;
        while (true) {
            int leftIdx = leftChild(idx);
            int rightIdx = rightChild(idx);
            if (leftIdx < heap.size() && heap.get(leftIdx).getData() > heap.get(maxIdx).getData()) {
                maxIdx = leftIdx;
            }
            if (rightIdx < heap.size() && heap.get(rightIdx).getData() > heap.get(maxIdx).getData()) {
                maxIdx = rightIdx;
            }
            if (maxIdx != idx) {
                swap(idx, maxIdx);
                idx = maxIdx;
            } else {
                return;
            }
        }
    }

    public void printHeapVisual() {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty.");
            return;
        }
        printHeapVisual(0, "", true);
    }

    private void printHeapVisual(int index, String prefix, boolean isTail) {
        if (index < heap.size()) {
            String nextPrefix = prefix + (isTail ? "    " : "│   ");
            int leftChildIdx = leftChild(index);
            int rightChildIdx = rightChild(index);

            if (rightChildIdx < heap.size()) {
                printHeapVisual(rightChildIdx, nextPrefix, false);
            }
            System.out.println(prefix + (isTail ? "└── " : "├── ") + heap.get(index));
            if (leftChildIdx < heap.size()) {
                printHeapVisual(leftChildIdx, nextPrefix, true);
            }
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(7);
        heap.insert(8);
        heap.insert(5);
        heap.insert(6);
        heap.insert(3);
        heap.insert(4);

        heap.printHeapVisual();
    }
}
