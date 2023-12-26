class Node {
    int data;
    Node next;
    Node(int d) // Constructor
    {
        data = d;
        next = null;
    }
}

class LUCLinkedList {// a Singly Linked List
    Node head; // head of list

    public void insert (int data) // Method to insert a new node
    {
        Node new_node = new Node(data); // Create a new node with given data
        new_node.next = null;
        if (head == null) { // If the Linked List is empty, then make the new node as head
            head = new_node;
        } else {// Else traverse till the last node and insert the new_node there
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node; // Insert the new_node at last node
        }
    }

    public void insertList(int[] insertList, int size) {
        for (int i = 0; i < size; i++) {
            insert(insertList[i]);
        }
    }

    public int findMax() {
        int max = -1;
        Node curr = head;

        while (curr != null) {
            if (max < curr.data)
                max = curr.data;
            curr = curr.next;
        }
        return max;
    }

    public void printEven() {
        Node curr = head;

        System.out.print("Even List: ");
        while (curr != null) {
            if (curr.data % 2 == 0) {
                System.out.println(curr.data + " ");
            }
            curr = curr.next;
        }
    }

    public void removeMatch(int data) {
        Node curr = head;

        if (curr == null) {
            throw new NullPointerException();
        }
        if (curr.data == data) {
            curr = curr.next;
        }
        while (curr.next != null) {
            if (curr.data == data) {
                curr = curr.next.next;
            }
        }
    }


    public void printList() // Method to print the LinkedList.
    {
        Node currNode = head;
        System.out.print("LinkedList: ");
        while (currNode != null) { // Traverse through the LinkedList
            System.out.print(currNode.data + " "); // Print the data at current node
            currNode = currNode.next; // Go to next node
        }
    }
}

public class LUCLinkedListMain {
    public static void main(String[] args)
    {
        LUCLinkedList list = new LUCLinkedList();/* Start with the empty list. */
        // Insert the values
        list.insert(2);
        list.insert(2);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(2);
        list.insert(2);
        list.printList(); // Print the LinkedList
    }
}
