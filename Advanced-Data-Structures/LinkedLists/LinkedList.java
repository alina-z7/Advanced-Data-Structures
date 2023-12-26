public class LinkedList {

    class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            next = null;
        }

        public String toString() {
            return "[" + data + "] -> ";
        }
    }
    private ListNode head, tail;
    private int length;

    public LinkedList(int data) {
        ListNode newNode = new ListNode(data);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void append(int data) {
        ListNode newNode = new ListNode(data);
        if (length == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

    public ListNode removeLast() {
        if (length == 0) {
            return null;
        }
        ListNode curr = head, prev = head;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        tail = prev;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return prev;
    }

    public void prepend(int data) {
        ListNode newNode = new ListNode(data);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public ListNode removeFirst() {
        if (length == 0) {
            return null;
        }
        ListNode first = head;
        head = head.next;
        first.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return first;
    }

    public ListNode get(int idx) {
        if (idx == 0) {
            return head;
        }
        if (idx == length - 1) {
            return tail;
        }
        ListNode curr = head;
        int count = 0;
        while (curr.next != null) {
            curr = curr.next;
            count++;
            if (count == idx) {
                return curr;
            }
        }
        return null;
    }

    public boolean set(int idx, int data) {
        if (idx < 0 || idx >= length) {
            return false;
        }
        if (idx == 0) {
            head.data = data;
            return true;
        }
        if (idx == length - 1) {
            tail.data = data;
            return true;
        }
        ListNode curr = get(idx);
        curr.data = data;
        return true;
    }

    public ListNode remove(int idx) {
        if (idx < 0 || idx >= length) {
            return null;
        }
        if (idx == 0) {
            return removeFirst();
        }
        if (idx == length - 1) {
            return removeLast();
        }
        ListNode prev = get(idx - 1), rmv = get(idx);
        prev.next = rmv.next;
        rmv.next = null;
        length--;
        return rmv;
    }

    public boolean insert(int idx, int data) {
        if (idx < 0 || idx > length) {
            return false;
        }
        if (idx == 0) {
            prepend(data);
            return true;
        }
        if (idx == length) {
            append(data);
            return true;
        }
        ListNode curr = get(idx), prev = get(idx - 1);
        ListNode newNode = new ListNode(data);
        prev.next = newNode;
        newNode.next = curr;
        return true;
    }

    public void reverse() {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public String toString() {
        ListNode curr = head;
        StringBuilder br = new StringBuilder("LL: | -> ");
        while (curr != null) {
            br.append(curr);
            curr = curr.next;
        }
        return br + "null";
    }

    public static void main(String[] args) {
            //System.out.println("long Linked-Lists... ");

        LinkedList newLL = new LinkedList(1);
        System.out.println("Original list: \n" + newLL + "\n");

        newLL.append(2);
        newLL.append(3);
        newLL.append(4);
        newLL.append(5);
        System.out.println("Adding elements: \n" + newLL + "\n");

        newLL.removeLast();
        System.out.println("Removing last element: \n" + newLL + "\n");

        newLL.prepend(0);
        newLL.prepend(-1);
        System.out.println("Adding to the beginning of list: \n" + newLL + "\n");

        newLL.removeFirst();
        System.out.println("Removing first element: \n" + newLL + "\n");

        System.out.println("Get element at index: \n" + newLL.get(-1) + "\n");

        newLL.set(3, 5);
        System.out.println("Replacing element value at index: \n" + newLL + "\n");

        newLL.insert(2, 3);
        System.out.println("Inserting element at index: \n" + newLL + "\n");

        newLL.remove(3);
        System.out.println("Removing element at index: \n" + newLL + "\n");

        newLL.reverse();
        System.out.println("Reversing a list: \n" + newLL + "\n");
    }

}
