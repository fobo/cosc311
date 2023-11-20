package cosc311p3;

//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #3 - Version 1


//This is the "data" object that will sit inside of a specific node.

public class IndexRecord 
{
	private String key;
	private int where;

	public IndexRecord(String key, int where) 
	{
		this.key = key;
		this.where = where;
	}

	@Override
	public String toString() 
	{
		return key + " " + where;
	}

	public int compareTo(IndexRecord otherKey) 
	{
		return this.key.compareTo(otherKey.key);
	}

	public String getKey() 
	{
		return key;
	}

	public void setKey(String key) 
	{
		this.key = key;
	}

	public int getWhere() 
	{
		return where;
	}

	public void setWhere(int where) 
	{
		this.where = where;
	}
}