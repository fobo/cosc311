package cosc311p3;

//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #3 - Version 1
public class BinarySearchTree {

    private Node root;
    private DataBase database;

    public BinarySearchTree(DataBase database) {
        this.root = null;
        this.database = database; //passing reference to itself so we can use parent methods (like print)
    }

//    public void insert(IndexRecord data) {
//        root = insertRecursive(root, data);
//    }
//
//    private Node insertRecursive(Node root, IndexRecord data) {
//        if (root == null) {
//            return new Node(data);
//        }
//
//        if (data.getKey().compareTo(root.data.getKey()) < 0) {
//            root.left = insertRecursive(root.left, data);
//        } else if (data.getKey().compareTo(root.data.getKey()) > 0) {
//            root.right = insertRecursive(root.right, data);
//        }
//
//        return root;
//    }
    
    public void insert(IndexRecord data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node root, IndexRecord data) {
        if (root == null) {
            return new Node(data);
        }

        // Handle duplicates by inserting into the left subtree
        if (data.getKey().compareTo(root.data.getKey()) <= 0) {
            root.left = insertRecursive(root.left, data);
        } else {
            root.right = insertRecursive(root.right, data);
        }

        return root;
    }

    
    public int findNode(String key) {
        return findNodeRecursive(root, key);
    }

    private int findNodeRecursive(Node root, String key) {
        if (root == null) {
            return -1; // Key not found
        }

        int compareResult = key.compareTo(root.data.getKey());

        if (compareResult == 0) {
        	System.out.println("find location: " + root.data.getWhere());
            return root.data.getWhere(); // Key found, return the location
        } else if (compareResult < 0) {
            return findNodeRecursive(root.left, key); // Search in the left subtree
        } else {
            return findNodeRecursive(root.right, key); // Search in the right subtree
        }
    }
    
    
    public void deleteIndexRecord(int where) {
        root = deleteIndexRecordRecursive(root, where);
        System.out.println("deleteIndexRecord " + where);
    }

    private Node deleteIndexRecordRecursive(Node root, int where) {
        // Base case: If the tree is empty

        if (root == null) {
        	System.out.println("Node is empty");
            return null;
        }
    	System.out.println("root where: " + root.data.getWhere());
    	System.out.println("Target where: " + where);
        // Recursively search for the node to be deleted
        if (where < root.data.getWhere()) {
            root.left = deleteIndexRecordRecursive(root.left, where);
        } else if (where > root.data.getWhere()) {
            root.right = deleteIndexRecordRecursive(root.right, where);
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
            root.right = deleteIndexRecordRecursive(root.right, root.data.getWhere());
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
        if (root != null) {
            inOrderTraversalRecursive(root.left);
            
            database.printRecord(root.data.getWhere());
            //root.displayNode(); 
            inOrderTraversalRecursive(root.right);
        }
    }
    public void reverseOrderTraversal() {
        reverseOrderTraversalRecursive(root);
    }

    private void reverseOrderTraversalRecursive(Node root) {
        if (root != null) {
            reverseOrderTraversalRecursive(root.right);
            database.printRecord(root.data.getWhere()); 
            reverseOrderTraversalRecursive(root.left);
        }
    }
}
