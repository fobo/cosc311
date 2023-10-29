package cosc311p2;

//Node holds the object containing the relevant data, as well as pointers to the next/previous node.
public class Node {

    IndexRecArray data; 
    Node prev; 
    Node next; 
  
    public Node(IndexRecArray data) 
    { 
        this.data = data; 
        this.prev = null; 
        this.next = null; 
    } 
}
