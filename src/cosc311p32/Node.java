package cosc311p32;


//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #3 - Version 2
//Node holds the object containing the relevant data, as well as pointers to the next/previous node.
public class Node {

    IndexRecord data;
    Node left, right;
    boolean isLeftThreaded, isRightThreaded;

    public Node(IndexRecord data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.isLeftThreaded = false;
        this.isRightThreaded = false;
    }
    public void displayNode() {
        System.out.println(data + "\n");
    }
}
