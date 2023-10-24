package cosc311p2;



// Where the sorted data sits
public class IndexRecArray 
{
	private String key;
	private int where;

	public IndexRecArray(String key, int where) 
	{
		this.key = key;
		this.where = where;
	}

	@Override
	public String toString() 
	{
		return key + " " + where;
	}

	public int compareTo(IndexRecArray otherKey) 
	{
		return this.key.compareTo(otherKey.key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getWhere() {
		return where;
	}

	public void setWhere(int where) {
		this.where = where;
	}
	

}
