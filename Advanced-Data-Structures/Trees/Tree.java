import java.util.*;
import java.util.LinkedList;

public class Tree {

    public Tree() {
        root = null;
    }

    public Tree(int val) {
        root = new Node(val);
    }

    static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
        Node parent = null;

        public Node(int d) {
            data = d;
        }


        public Node addChild(int d) {
            Node n = new Node(d);
            n.setParent(this);
            children.add(n);
            return n;
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public void setParent(Node p) {
            parent = p;
        }

        public Node getParent() {
            return parent;
        }
    }

    Node root = null;

    // Your code here:
    public void traverseByLevel() {
                Node curr = root;
                if (curr == null) {
                    return;
                }

                Queue<Node> queue = new LinkedList<>();
                queue.offer(curr);

                StringBuilder levelOrderPrint = new StringBuilder();

                while (!queue.isEmpty()) {
                    curr = queue.poll();
                    levelOrderPrint.append(curr.data).append(" ");

                    for (Node child : curr.getChildren()) {
                        if (child != null) {
                            queue.offer(child);
                        }
                    }
                }
                System.out.println(levelOrderPrint.toString().trim());
            }

            public static void main(String[] args) {
                Tree myTree = new Tree(1);
                Tree.Node n2 = myTree.root.addChild(2);
                Tree.Node n3 = myTree.root.addChild(3);
                n2.addChild(4);
                n2.addChild(5);
                n2.addChild(6);
                n3.addChild(7);
                n3.addChild(8);
                n3.addChild(9);
                // Should print: 1 2 3 4 5 6 7 8 9
                myTree.traverseByLevel();
            }
        }

