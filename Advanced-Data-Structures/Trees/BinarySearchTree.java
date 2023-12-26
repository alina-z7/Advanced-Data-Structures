
public class BinarySearchTree {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        public TreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
        public String toString() {
            return "[" + data + "]";
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public TreeNode insertAll(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            root = insertAllHelper(root, arr[i]);
        }
        return root;
    }

    private TreeNode insertAllHelper(TreeNode curr, int val) {
        if (curr == null) {
            return new TreeNode(val);
        }
        if (val < curr.data) {
            curr.left = insertAllHelper(curr.left, val);
        } else {
            curr.right = insertAllHelper(curr.right, val);
        }
        return curr;
    }

    public boolean insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
            return true;
        }
        TreeNode curr = root;
        while (true) {
            if (newNode.data == curr.data) {
                return false;
            }
            if (newNode.data < curr.data) {
                if (curr.left == null) {
                    curr.left = newNode;
                    return true;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = newNode;
                    return true;
                }
                curr = curr.right;
            }
        }
    }

    public void rInsert(int val) {
        if (root == null)
            root = new TreeNode(val);
        rInsert(root, val);
    }

    private TreeNode rInsert(TreeNode curr, int val) {
        // check if root is null, to plug in a new node
        if (curr == null) {
            return new TreeNode(val);
        }
        // if val exists, no insertion
        if (val < curr.data) {
            curr.left = rInsert(curr.left, val);
        } else {
             curr.right = rInsert(curr.right, val);
        }
        // return reference to root
        return curr;
    }

    public boolean contains(int val) {
        TreeNode curr = root;
        while (curr != null) {
            if (val < curr.data)
                curr = curr.left;
            else if (val > curr.data)
                curr = curr.right;
            else return true;
        }
        return false;
    }

    public boolean rContains(int val) {
        return rContains(root, val);
    }

    private boolean rContains(TreeNode curr, int val) {
        if (curr == null) return false;
        if (curr.data == val) return true;
        if (val < curr.data) {
            return rContains(curr.left, val);
        } else {
            return rContains(curr.right, val);
        }
    }

    public void delete(int val) {
        if (root == null) return;
        delete(root, val);
    }

    private TreeNode delete(TreeNode curr, int val) {
        if (curr == null) return null;
        if (val < curr.data) {
            curr.left = delete(curr.left, val);
        } else if (val > curr.data){
            curr.right = delete(curr.right, val);
        } else {
            if (curr.left == null && curr.right == null) {
                return null;
            }
            else if (curr.left == null) {
                curr = curr.right;
            }
            else if (curr.right == null) {
                curr = curr.left;
            } else {
                int subTreeMin = minVal(curr.right);
                curr.data = subTreeMin;
                curr.right = delete(curr.right, subTreeMin);
            }
        }
        return curr;
    }

    public int minVal() {
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.data;
    }

    public int minVal(TreeNode curr) {
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    public int maxVal() {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(TreeNode node, String prefix, boolean isTail) {
        if (node != null) {
            String nextPrefix = prefix + (isTail ? "    " : "│   ");
            if (node.right != null) {
                printTree(node.right, nextPrefix, false);
            }
            System.out.println(prefix + (isTail ? "└── " : "┌── ") + node);
            if (node.left != null) {
                printTree(node.left, nextPrefix, true);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(47);
        bst.insert(21);
        bst.insert(18);
        bst.insert(76);
        bst.insert(27);
        bst.insert(52);
        bst.insert(82);

        System.out.println("Original BST: \n");
        bst.printTree();

        System.out.println("\nPrints if the BST contains a value (27): " + bst.rContains(27));
        System.out.println("Prints if the BST contains a value (17): " + bst.contains(17));

        System.out.println("\nMore insertions in BST: ");
        bst.rInsert(17);
        System.out.println("\nInsert value (17): \n");
        bst.printTree();

        bst.rInsert(51);
        System.out.println("\nInsert value (51): \n");
        bst.printTree();

        System.out.println("\nDelete a node given the value (18): \n");
        bst.delete(18);
        bst.printTree();

        System.out.println("\nThe maximum value in the BST: " + bst.maxVal());
        System.out.println("The minimum value in the BST: " + bst.minVal());

        BinarySearchTree arrayTree = new BinarySearchTree();
        arrayTree.insertAll(new int[] {3, 6, 4, 8, 2, 11});
        arrayTree.printTree();
    }
}
