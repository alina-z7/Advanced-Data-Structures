public class DoublyLinkedList {

    final class DoublyListNode {
        int data;
        DoublyListNode next;
        DoublyListNode prev;
        public DoublyListNode(int data) {
            this.data = data;
            next = null;
            prev = null;
        }

        public String toString() {
            return "<- [" + data + "] -> ";
        }
    }
    private DoublyListNode head;
    private DoublyListNode tail;
    private int length;

    public DoublyLinkedList(int data) {
        DoublyListNode newNode = new DoublyListNode(data);
        head = newNode;
        tail = newNode;
        length++;
    }

    public void append(int data) {
        DoublyListNode newNode = new DoublyListNode(data);
        if (length == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        length++;
    }

    public DoublyListNode removeLast() {
        if (length == 0) {
            return null;
        }
        DoublyListNode rmv = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            rmv.prev = null;
        }
        length--;
        return rmv;
    }

    public void prepend(int data) {
        DoublyListNode newNode = new DoublyListNode(data);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public DoublyListNode removeFirst() {
        if (length == 0) {
            return null;
        }
        DoublyListNode curr = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            curr.next = null;
        }
        length--;
        return curr;
    }

    public DoublyListNode get(int idx) {
        if (idx == 0) {
            return head;
        }
        if (idx == length - 1) {
            return tail;
        }
        if (idx >= length || idx < 0) {
            return null;
        }
        DoublyListNode curr = head;
        if (idx < length/2) {
            for (int i = 0; i < idx; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = length - 1; i > idx; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    public boolean set(int idx, int data) {
        DoublyListNode curr = get(idx);
        if (curr != null) {
            curr.data = data;
            return true;
        }
        return false;
    }

    public boolean insert(int idx, int data) {
        if (idx == 0) {
            prepend(data);
            return true;
        }
        if (idx == length) {
            append(data);
            return true;
        }
        if (idx > length || idx < 0) {
            return false;
        } else {
            DoublyListNode newNode = new DoublyListNode(data);
            DoublyListNode before = get(idx - 1);
            DoublyListNode after = before.next;
            before.next = newNode;
            after.prev = newNode;
            newNode.prev = before;
            newNode.next = after;
        }
        length++;
        return true;
    }

    public DoublyListNode remove(int idx) {
        DoublyListNode curr = get(idx);
        if (idx == 0) {
            return removeFirst();
        }
        if (idx == length - 1) {
            return removeLast();
        }
        if (idx >= length || idx < 0) {
            return null;
        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.next = null;
            curr.prev = null;
        }
        length--;
        return curr;
    }


    public String toString() {
        DoublyListNode curr = head;
        StringBuilder br = new StringBuilder("DLL: null -> ");
        while (curr != null) {
            br.append(curr);
            curr = curr.next;
        }
        return br + "<- null";
    }

    public static void main(String[] args) {
        DoublyLinkedList newLL = new DoublyLinkedList(1);
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

        System.out.println("Get element at index: \n" + newLL.get(3) + "\n");

        newLL.set(3, 5);
        System.out.println("Replacing element value at index: \n" + newLL + "\n");

        newLL.insert(2, 3);
        System.out.println("Inserting element at index: \n" + newLL + "\n");

        newLL.remove(3);
        System.out.println("Removing element at index: \n" + newLL + "\n");

        //newLL.reverse();
        System.out.println("Reversing a list: \n" + newLL + "\n");

    }

}

