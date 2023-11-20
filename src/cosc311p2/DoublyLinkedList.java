package cosc311p2;

//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #2
public class DoublyLinkedList 
{

	private Node head; // Double Ended feature allows us to go directly to the front or end.
	private Node tail;
	private int size;

	public DoublyLinkedList() 
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public boolean isEmpty() 
	{
		return head == null;
	}



	//Utilizes findRecord within DataBase class
	public int findNode(String key) 
	{
	    Node current = head;

	    while (current != null) 
	    {
	        if (current.data.getKey().equals(key)) 
	        {
	            return current.data.getWhere();
	        }
	        current = current.next;
	    }

	    // Default bad case, no record found
	    return -1;
	}
	
	
	
	
	
	
	
	
	public void insert(IndexRecord indexRecord) 
	{
	    Node newNode = new Node(indexRecord);

	    if (head == null || indexRecord.compareTo(head.data) < 0) 
	    {
	        newNode.next = head;
	        newNode.prev = null;
	        if (head != null) 
	        {
	            head.prev = newNode;
	        }
	        head = newNode;

	        if (tail == null) 
	        {
	            // If the list was empty, update the tail to the new node
	            tail = newNode;
	        }

	        size++;
	        return;
	    }

	    Node current = head;
	    while (current.next != null && indexRecord.compareTo(current.next.data) > 0) 
	    {
	        current = current.next;
	    }

	    newNode.next = current.next;
	    newNode.prev = current;
	    if (current.next != null) 
	    {
	        current.next.prev = newNode;
	    }
	    current.next = newNode;

	    if (newNode.next == null) 
	    {
	        // If the new node is inserted at the end, update the tail to the new node
	        tail = newNode;
	    }

	    size++;
	}


	
	
	
	
	public Node deleteIndexRecord(int where) 
	{
	    // Check for an empty list
	    if (head == null) {
	        System.out.println("List is empty.");
	        return null;
	    }

	    Node current = head; // start at the beginning

	    while (current != null && current.data.getWhere() != where) 
	    {
	        current = current.next; // move to the next node
	    }

	    if (current == null) 
	    {
	        System.out.println("Record with 'where' value " + where + " not found.");
	        return null; // didn't find it, check for null on delete method to report to the user.
	    }

	    if (current == head) 
	    {
	        head = current.next; // first item; update head
	        if (head != null) 
	        {
	            head.prev = null; // remove the previous reference
	        }
	    } else 
	    {
	        current.prev.next = current.next; // not first; update previous node's next
	    }

	    if (current == tail) 
	    {
	        tail = current.prev; // last item; update tail
	        if (tail != null) 
	        {
	            tail.next = null; // remove the next reference
	        }
	    } else 
	    {
	        current.next.prev = current.prev; // not last; update next node's previous
	    }

	    return current; // return the deleted node to print to the user.
	}

	
	
	
	
	//Iterators
	public Node iteratorInitHead() 
	{
		return head;
	}
	public Node iteratorInitTail() 
	{
		return tail;
	}
	public Node getHead() 
	{
		return head;
	}

	public void setHead(Node head) 
	{
		this.head = head;
	}

	public Node getTail() 
	{
		return tail;
	}

	public void setTail(Node tail) 
	{
		this.tail = tail;
	}

	public boolean hasNext(Node currentNode) 
	{
	    return currentNode != null && currentNode.next != null;
	}

	public boolean hasPrev(Node currentNode) 
	{
	    return currentNode != null && currentNode.prev != null;
	}

    public Node getNext(Node currentNode) 
    {
        if (currentNode != null)
        {
            return currentNode.next;
        }
        return null;
    }

    
    
    
    
    // Get the previous node relative to the current node
    public Node getPrev(Node currentNode)
    {
        if (currentNode != null)
        {
            return currentNode.prev;
        }
        return null;
    }
}
