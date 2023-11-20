package cosc311p3;

import cosc311p3.IndexRecord;
import cosc311p3.Node;

//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #3 - Version 1
//Node holds the object containing the relevant data, as well as pointers to the next/previous node.
public class Node {

	IndexRecord data;
	Node left;
	Node right;

	public Node(IndexRecord data) // constructor
	{
		this.data = data;
		this.left = null;
		this.right = null;
	}
    public void displayNode() {
        System.out.println(data + "\n");
    }
}
