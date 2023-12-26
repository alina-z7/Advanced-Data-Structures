public class LUCLL { // a Singly Linked List
        static class Node {
            int data;
            Node next;
            Node(int d) // Constructor
            {
                data = d;
                next = null;
            }
        }
        Node head; // head of list
        public void insert(int data) // Method to insert a new node
        {
            Node new_node = new Node(data); // Create a new node with given data
            new_node.next = null;
            if (this.head == null) { // If the Linked List is empty, then make the new node as head
                this.head = new_node;
            }
            else {// Else traverse till the last node and insert the new_node there
                Node last = this.head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = new_node; // Insert the new_node at last node
            }
        }

        public String toString() // Method to output the LinkedList as a String
        {   String output="[";
            Node currNode = this.head;
            //System.out.print("LinkedList: ");
            while (currNode != null) { // Traverse through the LinkedList
                //System.out.print(currNode.data + " "); // Print the data at current node
                output+=currNode.data + " ";
                currNode = currNode.next; // Go to next node
            }
            return output.trim()+"]";
        }

        //remove all the nodes that store a given value less than the passed value (ltValue)
        public void removeElementsLT(int ltValue) {
            // Using two pointers, initialize a pointer for the node previous to the head as null, and curr
            // for the head iterator
            Node prev = null, curr = head;

            // while the curr element is not null see if that element < val,
            while (curr != null) {
            // if yes, then if the previous element is not null in which the case would in the first iteration,
                if (curr.data < ltValue) {
            // set the prev next pointer to curr's pointer
            // increment curr
                if (prev != null)  {
                        prev.next = curr.next;
                        curr = curr.next;
                }
                else  {
            // else if this is the first iteration have the list start at the curr's next element, so both prev
            // and curr remain != null
            // increment curr
                    head = curr.next;
                    curr = curr.next;
                    }
                } else {
            // if the curr.data is !< val, increment the prev and curr to the next two values
                prev = curr;
                curr = curr.next;
                }
            }
        }


        public static void main(String[] args)
        {
            LUCLL list = new LUCLL();/* Start with the empty list. */
            // Insert the values
            list.insert(1);
            list.insert(2);
            list.insert(3);
            list.insert(4);
            list.insert(1);
            list.insert(2);
            list.insert(3);
            list.insert(4);
            list.insert(9);
            list.insert(100);
            list.insert(35);
            System.out.println(list); // Print the LinkedList
            list.removeElementsLT(8);
            // Should return [9, 100, 35]
            System.out.println(list);
    }
}
