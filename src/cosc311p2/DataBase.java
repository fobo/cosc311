package cosc311p2;

import java.util.Map;
import java.util.Scanner;

//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #2

public class DataBase 
{
	private DataBaseArray myDB;
	DoublyLinkedList ID, First, Last;

	public DataBase() 
	{

		myDB = new DataBaseArray(100);
		ID = new DoublyLinkedList();
		First = new DoublyLinkedList();
		Last = new DoublyLinkedList();
	}

	private int nextDBRec; // This gets used to track where in the database array, the current empty
							// position is to insert data

	// Insert method
	public void insertRecord(DataBaseRec record) 
	{

		// Check to see if ID already exists in the Linked List
		if (findRecord(record.getID(), ID) == -1) 
		{
			DataBaseRec recordCopy = new DataBaseRec(record.getFname(), record.getLname(), record.getID());

			// Create an IndexRec object for the record with ID, First Name, and Last Name
			IndexRecord idRec = new IndexRecord(recordCopy.getID(), nextDBRec);
			IndexRecord firstRec = new IndexRecord(recordCopy.getFname(), nextDBRec);
			IndexRecord lastRec = new IndexRecord(recordCopy.getLname(), nextDBRec);

			// Insert the IndexRec objects into the respective Linked Lists
			ID.insert(idRec);
			First.insert(firstRec);
			Last.insert(lastRec);

			// Insert the record into the myDB array
			myDB.dataBaseRecArray[nextDBRec] = recordCopy;

			// Increment nextDBRec to track the next empty position
			nextDBRec++;
			System.out.println("Record added successfully.");
		} else 
		{
			System.out.println("Record with ID " + record.getID() + " already exists.");
		}

	}

	// Builds the string for outputting data
	@Override
	public String toString() 
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < nextDBRec; i++) 
		{
			stringBuilder.append("  ").append(myDB.dataBaseRecArray[i]).append("\n");
		}
		return stringBuilder.toString();
	}

	// Modular List Method(s)
	public void addIt() 
	{

		String input;
		DataBaseRec dbr = new DataBaseRec();
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter First Name Last Name ID (separated by spaces): ");
		input = scan.nextLine();

		String[] data = input.split(" "); // Space separated values are stored into a String array

		if (data.length == 3) 
		{
			dbr.setData(data[0], data[1], data[2]);
			insertRecord(dbr);

		} else {
			System.out.println("Invalid input. Please provide First Name, Last Name, and ID separated by spaces.");
		}
		System.out.println("===Press enter to continue===");
		scan.nextLine();

	}

	public void deleteIt() 
	{
		Scanner findID = new Scanner(System.in);
		String input;
		System.out.print("Enter the ID of the student you wish to remove: ");
		input = findID.nextLine();
		int where = findRecord(input, ID);

		// Delete the found record
		if (where != -1) 
		{
			System.out.println("Record with ID " + myDB.dataBaseRecArray[where] + " has been deleted.");
			ID.deleteIndexRecord(where);
			First.deleteIndexRecord(where);
			Last.deleteIndexRecord(where);

		} else {
			System.out.println("Record cannot be found.");
		}

	}

	// Feed this the ID linked list, and the key location
	public int findRecord(String key, DoublyLinkedList linkedList) 
	{
		int where = linkedList.findNode(key);
		return where;
	}

	public void findIt() 
	{

		String input;

		Scanner findID = new Scanner(System.in);
		System.out.print("Enter the ID of the student you wish to find: ");
		input = findID.nextLine();
		int where = findRecord(input, ID);
		if (where == -1) 
		{
			System.out.println("Record with ID " + input + " not found.");
		} else 
		{
			System.out.println("Record found: " + myDB.dataBaseRecArray[where]);
		}
		System.out.println("===Press enter to continue===");
		findID.nextLine();

	}

	// Generalized print method for all lists
	public void listByField(DoublyLinkedList field, boolean ascending) 
	{
		if (field == null) 
		{
			throw new IllegalArgumentException("Invalid field name");
		}

		if (ascending) // Print list in forwards order!!
		{ 
			Node current = field.getHead();
			while (current != null) 
			{
				int where = current.data.getWhere();
				System.out.println(myDB.dataBaseRecArray[where].toString());
				current = current.next; // Move to the next node
			}
		} else 
		{ // Print list in reverse order!!
			Node current = field.getTail();
			while (current != null) 
			{
				int where = current.data.getWhere();
				System.out.println(myDB.dataBaseRecArray[where].toString());
				current = current.prev; // Move to the previous node
			}
		}
	}

	// Modular list methods
	public void ListByIDAscending() 
	{
		listByField(ID, true);
	}

	public void ListByFirstAscending() 
	{
		listByField(First, true);
	}

	public void ListByLastAscending() 
	{
		listByField(Last, true);
	}

	public void ListByIDDescending() 
	{
		listByField(ID, false);

	}

	public void ListByFirstDescending() 
	{
		listByField(First, false);
	}

	public void ListByLastDescending() 
	{
		listByField(Last, false);
	}
}
