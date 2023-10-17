package cosc311p1;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class Project {

	// This is one individual record of data, or one "row" of data
	public static class DataBaseRec {
		private String Fname;
		private String Lname;
		private String ID; // Expect ID to be 5 characters only.

		// Constructor
		public DataBaseRec(String fname, String lname, String id) {
			Fname = fname;
			Lname = lname;
			ID = id;
		}
		// Default Constructor
		public DataBaseRec() {
			
		}

		// Others
		public String getFname() {
			return Fname;
		}

		public void setFname(String fname) {
			Fname = fname;
		}

		public String getLname() {
			return Lname;
		}

		public void setLname(String lname) {
			Lname = lname;
		}

		public String getID() {
			return ID;
		}

		public void setID(String id) {
			ID = id;
		}
		public void setData(String fname, String lname, String id) {
			Fname = fname;
			Lname = lname;
			ID = id;
		}

		// toString
		@Override
		public String toString() {
			return ID + " " + Fname + " " + Lname;

		}

	}

	public static class DataBase {
		private DataBaseArray myDB;
		private IndexArray ID, First, Last;

		public DataBase() {
			myDB = new DataBaseArray(100);
			ID = new IndexArray(100);
			First = new IndexArray(100);
			Last = new IndexArray(100);
		}

		private int nextDBRec; // This gets used to track where in the database array, the current empty
								// position is to insert data

		// Insert method
		public void insertRecord(DataBaseRec record) {
			
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

		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < nextDBRec; i++) {
				stringBuilder.append("  ").append(myDB.dataBaseRecArray[i]).append("\n");
			}
			return stringBuilder.toString();
		}
		// Modular List Method(s)


		public void addIt() {
		    String input;
		    DataBaseRec dbr = new DataBaseRec();
		    Scanner scan = new Scanner(System.in);

		    System.out.print("Enter First Name Last Name ID (separated by spaces): ");
		    input = scan.nextLine();

		    String[] data = input.split(" ");

		    if (data.length == 3) {
		        dbr.setData(data[0], data[1], data[2]);
		        insertRecord(dbr);
		        System.out.println("Record added successfully.");
		    } else {
		        System.out.println("Invalid input. Please provide First Name, Last Name, and ID separated by spaces.");
		    }
		    System.out.println("===Press enter to continue===");
		    scan.nextLine();
		    
		}




		public void deleteIt() {
//		    int recordPosition = findIt();
//
//		    if (recordPosition != -1) {
//		        // Delete the found record
//		        ID.deleteIndexRecord(id);
//		        First.deleteIndexRecord(myDB.dataBaseRecArray[recordPosition].getFname());
//		        Last.deleteIndexRecord(myDB.dataBaseRecArray[recordPosition].getLname());
//		        
//		       
//
//		        System.out.println("Record with ID " + id + " has been deleted.");
//		    } else {
//		        System.out.println("Record with ID " + id + " not found.");
//		    }
			
		}


		public int findIt() {

		    String input;
		    
		    Scanner findID = new Scanner(System.in);

		    System.out.print("Enter the ID of the student you wish to find: ");
		    input = findID.nextLine();

		    
			
			// Binary search
		    int left = 0;
		    int right = ID.getSize() - 1;

		    while (left <= right) {
		        int mid = left + (right - left) / 2;
		        int comparison = myDB.dataBaseRecArray[ID.indexRecArray[mid].where].getID().compareTo(input);

		        if (comparison == 0) {
		            // Found the record with the target ID
		            System.out.println("Record found: " + myDB.dataBaseRecArray[ID.indexRecArray[mid].where]);
		            System.out.println("===Press enter to continue===");
				    findID.nextLine();
				    
		            return ID.indexRecArray[mid].where;
		        } else if (comparison < 0) {
		            // The target ID is in the right half of the current range
		            left = mid + 1;
		        } else {
		            // The target ID is in the left half of the current range
		            right = mid - 1;
		        }
		        
		    }

		    // If the loop completes without finding the record, it's not in the database
		    System.out.println("Record with ID " + input + " not found.");
		    System.out.println("===Press enter to continue===");
		    findID.nextLine();
		    return -1; // TODO: record not found
		}

	
		
		public void listByField(String field, boolean ascending) {
		    IndexArray selectedArray;

		    if (field.equals("ID")) {
		        selectedArray = ID;
		    } else if (field.equals("First")) {
		        selectedArray = First;
		    } else if (field.equals("Last")) {
		        selectedArray = Last;
		    } else {
		        throw new IllegalArgumentException("Invalid field name");
		    }

		    selectedArray.iteratorInitFront(); // Initialize the selected array

		    if (!ascending) {
		        selectedArray.iteratorInitBack(); // Initialize for descending order
		    }

		    while (selectedArray.hasNext()) {
		        int where = ascending ? selectedArray.getNext() : selectedArray.getPrevious();
		        System.out.println(myDB.dataBaseRecArray[where].toString());
		    }

		}


		public void ListByIDAscending() {
			listByField("ID", true);
		}


		public void ListByFirstAscending() {
			listByField("First", true); 
		}


		public void ListByLastAscending() {
			listByField("Last", true);

		}


		public void ListByIDDescending() {
			listByField("ID", false);

		}


		public void ListByFirstDescending() {
			listByField("First", false);
		}


		public void ListByLastDescending() {
			listByField("Last", false);
		}
	}

	// This is the "primary" array, used to store the complete set of records
	public static class DataBaseArray {
		private DataBaseRec[] dataBaseRecArray;
		private int size;

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		// Constructor to set the size of our "primary" array
		public DataBaseArray(int sz) {
			dataBaseRecArray = new DataBaseRec[sz];
			size = 0;
		}

	}

	// This is the basic class where all of the individual index records
	public static class IndexArray {
		private IndexRecArray[] indexRecArray;
		private int size;
		private int iterator;
		private boolean ascending;

		public void insert(IndexRecArray indexRecord) { // Insertion loop
			int j;
			for (j = size - 1; j >= 0; j--) {
				if ((indexRecArray[j].compareTo(indexRecord)) < 0)
					break;
				indexRecArray[j + 1] = indexRecArray[j];
			}
			indexRecArray[j + 1] = indexRecord;
			size++;
		}

		public void display() {
			int j;
			for (j = 0; j < size; j++)
				System.out.println(indexRecArray[j]);
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public IndexArray(int sz) {
			indexRecArray = new IndexRecArray[sz];
			size = 0;
			iterator = 0;
		}

		// set the iterator to zero
		void iteratorInitFront() {
			ascending = true;
			iterator = 0;
		}

		// set the iterator to the last element in the array
		void iteratorInitBack() {
			ascending = false;
			iterator = size - 1;
		}

		// returns true if iterator<= current last index in the array, false otherwise.
		boolean hasNext() {
			return ascending ? iterator <= size - 1 : iterator >= 0; // can probably combine this with hasPrevious
		}

		// returns true if iterator>0 , false otherwise
		boolean hasPrevious() {
			return ascending ? iterator >= 0 : iterator <= size - 1;
		}

		// returns the where component of the IndexRecord referenced by iterator and
		// then increments the iterator
	    public int getNext() {
	        if (ascending) {
	            int where = indexRecArray[iterator].where;
	            iterator++;
	            return where;
	        } else {
	            throw new NoSuchElementException("No more elements in the reverse iteration.");
	        }
	    }

		// returns the where component of the IndexRecord referenced by iterator and
		// then decrements the iterator
	    public int getPrevious() {
	        if (!ascending) {
	            int where = indexRecArray[iterator].where;
	            iterator--;
	            return where;
	        } else {
	            throw new NoSuchElementException("No more elements in the reverse iteration.");
	        }
	    }
	    private void deleteIndexRecord(String key) {
	        // Iterate through the index array to find the record with the given key
	        int indexToRemove = -1;
	        for (int i = 0; i < size; i++) {
	            if (indexRecArray[i].key.equals(key)) {
	                indexToRemove = i;
	                break;
	            }
	        }

	        if (indexToRemove != -1) {
	            // Remove the record from the index array
	            for (int i = indexToRemove; i < size - 1; i++) {
	                indexRecArray[i] = indexRecArray[i + 1];
	            }
	            indexRecArray[size - 1] = null;
	            size--;
	        }
	    }

	}

	//Where the sorted data sits
	public static class IndexRecArray {
		private String key;
		private int where;

		public IndexRecArray(String key, int where) {
			this.key = key;
			this.where = where;
		}

		@Override
		public String toString() {
			return key + " " + where;
		}

		public int compareTo(IndexRecArray otherKey) {
			return this.key.compareTo(otherKey.key);
		}

	}



}