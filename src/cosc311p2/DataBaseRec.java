package cosc311p2;
//Kevin Diehr
//E01019091        
//COSC 311 - Fall 2023
//Program #2

//This is one individual record of data, or one "row" of data
	public class DataBaseRec 
	{
		private String Fname;
		private String Lname;
		private String ID; // Expect ID to be 5 characters only.

		// Constructor
		public DataBaseRec(String fname, String lname, String id) 
		{
			Fname = fname;
			Lname = lname;
			ID = id;
		}

		// Default Constructor
		public DataBaseRec() 
		{

		}

		// Others
		public String getFname() 
		{
			return Fname;
		}

		public void setFname(String fname) 
		{
			Fname = fname;
		}

		public String getLname() 
		{
			return Lname;
		}

		public void setLname(String lname) 
		{
			Lname = lname;
		}

		public String getID() 
		{
			return ID;
		}

		public void setID(String id) 
		{
			ID = id;
		}

		public void setData(String fname, String lname, String id) 
		{
			Fname = fname;
			Lname = lname;
			ID = id;
		}

		// toString
		@Override
		public String toString() 
		{
			return ID + " " + Fname + " " + Lname;

		}

	}