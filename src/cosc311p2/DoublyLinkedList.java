package cosc311p2;

/*
 * This is the replacement for "IndexArray" from project 1.
 */
public class DoublyLinkedList {

	private Node head; // Double Ended feature allows us to go directly to the front or end.
	private Node tail;
	private int size;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return head == null;
	}

	// insert first/last, delete first/last

	// TODO: Add insert arbitrary position and delete where key

	public int findNode(String key) {
	    Node current = head;

	    while (current != null) {
	        if (current.data.getKey().equals(key)) {
	            return current.data.getWhere();
	        }
	        current = current.next;
	    }

	    // Default bad case, no record found
	    return -1;
	}
	
	public void insert(IndexRecArray indexRecord) {
		Node newNode = new Node(indexRecord);

		// If the LinkedList is empty or the new node should be inserted at the
		// beginning
		if (head == null || indexRecord.compareTo(head.data) < 0) {
			newNode.next = head;
			newNode.prev = null;
			if (head != null) {
				head.prev = newNode;
			}
			head = newNode;
			size++;
			return;
		}

		// Find the position where the new node should be inserted
		Node current = head;
		while (current.next != null && indexRecord.compareTo(current.next.data) > 0) {
			current = current.next;
		}

		// Insert the new node
		newNode.next = current.next;
		newNode.prev = current;
		if (current.next != null) {
			current.next.prev = newNode;
		}
		current.next = newNode;
		size++;
	}

	public Node deleteIndexRecord(int where) {
	    Node current = head; // start at the beginning

	    while (current != null && current.data.compareTo(new IndexRecArray("", where)) != 0) {
	        current = current.next; // move to the next node
	    }


	    if (current == null) {
	        return null; // didn't find it, check for null on delete method to report to user.
	    }

	    if (current == head) {
	        head = current.next; // first item; update head
	    } else {
	        current.prev.next = current.next; // not first; update previous node's next
	    }

	    if (current == tail) {
	        tail = current.prev; // last item; update tail
	    } else {
	        current.next.prev = current.prev; // not last; update next node's previous
	    }

	    return current; // return the deleted node to print to user.
	}


	public void displayForward() {
		System.out.print("List (first-->last): ");
		Node current = head; // start at beginning
		while (current != null) // until end of list,
		{
			current.displayNode(); // display data
			current = current.next; // move to next link
		}
		System.out.println("");
	}

	public void displayBackward() {
		System.out.print("List (last-->first): ");
		Node current = tail; // start at end
		while (current != null) // until start of list,
		{
			current.displayNode(); // display data
			current = current.prev; // move to previous link
		}

		System.out.println("");
	}
}
