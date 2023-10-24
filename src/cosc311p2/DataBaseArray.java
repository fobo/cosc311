package cosc311p2;

// This is the "primary" array, used to store the complete set of records
public class DataBaseArray 
{
	public DataBaseRec[] dataBaseRecArray;
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) 
	{
		this.size = size;
	}

	// Constructor to set the size of our "primary" array
	public DataBaseArray(int sz) 
	{
		dataBaseRecArray = new DataBaseRec[sz];
		size = 0;
	}

}