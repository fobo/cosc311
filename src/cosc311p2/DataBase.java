package cosc311p2;

import java.util.Scanner;


public class DataBase 
{
	private DataBaseArray myDB;
	private IndexArray ID, First, Last;

	public DataBase() 
	{
		myDB = new DataBaseArray(100);
		ID = new IndexArray(100);
		First = new IndexArray(100);
		Last = new IndexArray(100);
	}

	private int nextDBRec; // This gets used to track where in the database array, the current empty
							// position is to insert data

	
	
	
	// Insert method
	public void insertRecord(DataBaseRec record) 
	{

		// Check to see if ID already exists in the ordered array
		if (findRecord(record.getID()) == -1) 
		{
			DataBaseRec recordCopy = new DataBaseRec(record.getFname(), record.getLname(), record.getID());

			// Create an IndexRec object for the record with ID, First Name, and Last Name
			IndexRecArray idRec = new IndexRecArray(recordCopy.getID(), nextDBRec);
			IndexRecArray firstRec = new IndexRecArray(recordCopy.getFname(), nextDBRec);
			IndexRecArray lastRec = new IndexRecArray(recordCopy.getLname(), nextDBRec);
			
			// Insert the IndexRec objects into the respective Index arrays
			ID.insert(idRec);
			First.insert(firstRec);
			Last.insert(lastRec);

			// Insert the record into the myDB array
			myDB.dataBaseRecArray[nextDBRec] = recordCopy;

			// Increment nextDBRec to track the next empty position
			nextDBRec++;
		} else 
		{
			System.out.println("Record with ID " + record.getID() + " already exists.");
		}

	}

	//Builds the string for outputting data
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
			System.out.println("Record added successfully.");
		} else 
		{
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
		int where = findRecord(input);
		
		// Delete the found record
		if (where != -1) 
		{
			System.out.println("Record with ID " + myDB.dataBaseRecArray[where] + " has been deleted.");
			ID.deleteIndexRecord(where);
			First.deleteIndexRecord(where);
			Last.deleteIndexRecord(where);

		} else 
		{
			System.out.println("Record cannot be found.");
		}

	}

	
	
	
	// General binary search to find records. Is used for delete, find, and
	// duplicates searches.
	public int findRecord(String key) 
	{

		// Binary search
		int left = 0;
		int right = ID.getSize() - 1;

		while (left <= right) 
		{

			int mid = left + (right - left) / 2;
			int comparison = ID.getIndexRecArray()[mid].getKey().compareTo(key);
			if (comparison == 0) 
			{
				return ID.getIndexRecArray()[mid].getWhere();
			} else if (comparison < 0) 
			{
				// The target ID is in the right half of the current range
				left = mid + 1;
			} else 
			{
				// The target ID is in the left half of the current range
				right = mid - 1;
			}

		}
		// default bad case, this means no record is found
		return -1;
	}

	public void findIt() 
	{

		String input;

		Scanner findID = new Scanner(System.in);
		System.out.print("Enter the ID of the student you wish to find: ");
		input = findID.nextLine();
		int where = findRecord(input);
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

	public void listByField(String field, boolean ascending) 
	{
		IndexArray selectedArray;

		if (field.equals("ID")) 
		{
			selectedArray = ID;
		} else if (field.equals("First")) 
		{
			selectedArray = First;
		} else if (field.equals("Last")) 
		{
			selectedArray = Last;
		} else 
		{
			throw new IllegalArgumentException("Invalid field name");
		}

		selectedArray.iteratorInitFront(); // Initialize the selected array

		if (!ascending) 
		{
			selectedArray.iteratorInitBack(); // Initialize for descending order
		}

		// Selects which direction the iterator will flow
		while (selectedArray.hasNext()) 
		{
			int where = ascending ? selectedArray.getNext() : selectedArray.getPrevious();
			System.out.println(myDB.dataBaseRecArray[where].toString());
		}

	}

	public void ListByIDAscending() 
	{
		listByField("ID", true);
	}

	public void ListByFirstAscending() 
	{
		listByField("First", true);
	}

	public void ListByLastAscending() 
	{
		listByField("Last", true);

	}

	
	public void ListByIDDescending() 
	{
		listByField("ID", false);

	}

	public void ListByFirstDescending() 
	{
		listByField("First", false);
	}

	public void ListByLastDescending() 
	{
		listByField("Last", false);
	}
}
