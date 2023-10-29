package cosc311p2;

import java.util.NoSuchElementException;

//This is the basic class where all of the individual index records
public class IndexArray {
	
	
	/*
	 * 
	 * 
	 * TODO:
	 * Change IndexArray to DoubleEndedDoublyLinkedList
	 * Method list:
	 * insert -> insertBefore This will maintain order in the linked list.
	 * display -> will display the current record, used for testing
	 * getters/setters -> self explanatory
	 * iterators -> might not need
	 * deleteIndexRecord -> will delete a note, and properly move the pointers to next/prev 
	 * 						and handle edge cases at front and back of linked list.
	 */
	private IndexRecArray[] indexRecArray;
	private int size;
	private int iterator;
	private boolean ascending;

	public void insert(IndexRecArray indexRecord) // Insertion loop
	{
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

	public IndexRecArray[] getIndexRecArray() {
		return indexRecArray;
	}

	public void setIndexRecArray(IndexRecArray[] indexRecArray) {
		this.indexRecArray = indexRecArray;
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
			int where = indexRecArray[iterator].getWhere();
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
			int where = indexRecArray[iterator].getWhere();
			iterator--;
			return where;
		} else {
			throw new NoSuchElementException("No more elements in the reverse iteration.");
		}
	}

	public void deleteIndexRecord(int where) {

		// Iterate through the index array to find the record with the given key
		int indexToRemove = -1;
		for (int i = 0; i < size; i++) {
			if (indexRecArray[i].getWhere() == where) {
				indexToRemove = i;
				break;
			}

		}
		if (indexToRemove != -1) {
			for (int i = indexToRemove; i < size - 1; i++) {
				indexRecArray[i] = indexRecArray[i + 1];
			}
			indexRecArray[size - 1] = null;
			size--;
		}
	}

}