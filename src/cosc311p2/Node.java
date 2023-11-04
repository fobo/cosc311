package cosc311p2;
//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #2
//Node holds the object containing the relevant data, as well as pointers to the next/previous node.
public class Node 
{

	IndexRecord data;
	Node prev;
	Node next;

	public Node(IndexRecord data) // constructor
	{
		this.data = data;
		this.prev = null;
		this.next = null;
	}

	public void displayNode() // display this link, useful for test cases
	{
		System.out.println(data + "");
	}
}
