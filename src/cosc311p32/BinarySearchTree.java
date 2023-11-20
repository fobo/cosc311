package cosc311p32;

//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #3 - Version 
public class BinarySearchTree {

    private Node root;
    private DataBase database;

    public BinarySearchTree(DataBase database) {
        this.root = null;
        this.database = database; //passing reference to itself so we can use parent methods (like print)
    }

    
    private Node insertRecursive(Node root, IndexRecord data, Node parent) {
        if (root == null) {
            Node newNode = new Node(data);
            newNode.isLeftThreaded = true;
            newNode.left = parent; // Thread to its in-order predecessor
            return newNode;
        }

        int compareResult = data.compareTo(root.data);

        if (compareResult <= 0) {
            if (!root.isLeftThreaded) {
                root.left = insertRecursive(root.left, data, root);
            } else {
                Node newNode = new Node(data);
                newNode.left = root.left;
                newNode.isLeftThreaded = true;
                root.left = newNode;
            }
        } else {
            if (!root.isRightThreaded) {
                root.right = insertRecursive(root.right, data, root);
            } else {
                Node newNode = new Node(data);
                newNode.right = root.right;
                newNode.isRightThreaded = true;
                root.right = newNode;
            }
        }

        return root;
    }

    public void insert(IndexRecord data) {
        root = insertRecursive(root, data, null);
    }

    
    public int findNode(String key) {
        return findNodeRecursive(root, key);
    }

    private int findNodeRecursive(Node root, String key) {
        while (root != null) {
            int compareResult = key.compareTo(root.data.getKey());

            if (compareResult == 0) {
                System.out.println("find location: " + root.data.getWhere());
                return root.data.getWhere(); // Key found, return the location
            } else if (compareResult < 0 && !root.isLeftThreaded) {
                root = root.left; // Search in the left subtree
            } else if (compareResult > 0 && !root.isRightThreaded) {
                root = root.right; // Search in the right subtree
            } else {
                break; // Follow the threaded link
            }
        }

        return -1; // Key not found
    }
    
    
    public void deleteIndexRecord(int where, String key) {
        root = deleteIndexRecordRecursive(root, where, key);
    }

    private Node deleteIndexRecordRecursive(Node root, int where, String key) {
        // Base case: If the tree is empty

        if (root == null) {
            return null;
        }
        // Recursively search for the node to be deleted
        if (key.compareTo(root.data.getKey()) <= 0 && where != root.data.getWhere()) {
            root.left = deleteIndexRecordRecursive(root.left, where, key);
        } else if (key.compareTo(root.data.getKey()) > 0) {
            root.right = deleteIndexRecordRecursive(root.right, where, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

           
            // Node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteIndexRecordRecursive(root.right, root.data.getWhere(), root.data.getKey());
        }

        return root;
    }

    private IndexRecord minValue(Node root) {
        IndexRecord minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
    
    
    
    public void inOrderTraversal() {
        inOrderTraversalRecursive(root);
    }

    private void inOrderTraversalRecursive(Node root) {
        Node current = leftMost(root); // Find the leftmost node

        while (current != null) {
            database.printRecord(current.data.getWhere());

            if (current.isRightThreaded) {
                current = current.right; // Follow the threaded link
            } else {
                current = leftMost(current.right); // Move to the leftmost node of the right subtree
            }
        }
    }

    private Node leftMost(Node node) {
        // Find the leftmost node in the subtree
        while (node != null && node.left != null && !node.isLeftThreaded) {
            node = node.left;
        }
        return node;
    }
    public void reverseOrderTraversal() {
        reverseOrderTraversalRecursive(root);
    }

    private void reverseOrderTraversalRecursive(Node root) {
        Node current = rightMost(root); // Find the rightmost node

        while (current != null) {
            database.printRecord(current.data.getWhere());

            if (current.isLeftThreaded) {
                current = current.left; // Follow the threaded link
            } else {
                current = rightMost(current.left); // Move to the rightmost node of the left subtree
            }
        }
    }

    private Node rightMost(Node node) {
        // Find the rightmost node in the subtree
        while (node != null && node.right != null && !node.isRightThreaded) {
            node = node.right;
        }
        return node;
    }

}
