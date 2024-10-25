/*****************************************************************************
 * Author: Grant Von Hagen (251307427)
 * Date: 7/24/2024
 * Program: HashDictionary.java
 * Description: Implements DictionaryADT.java, using a hashtable and separate linking
 ******************************************************************************/
import java.util.LinkedList;


public class HashDictionary implements DictionaryADT{

	private LinkedList<Data>[] table;
	private int size;
	private int numRecords;
	private final int key = 318211;
	
	
	/*
	 * Initializers, initializes a linked list to each bucket in the hash table
	 */
	public HashDictionary (int size) {
		
		this.size = size;
		this.numRecords = 0;
		table = new LinkedList[size];
		
		for (int i = 0; i < size; i++)
		{
			table[i] = new LinkedList<>();
		}

		
	}
	
	
	/*
	 * Computes string into a hash, and is appended into the table
	 * 
	 */
	private int hash(String config)
	{
		int base = key;
		int hash = 0; // holder
		for (int i = 0; i < config.length(); i++)
		{
			//I think this how you do horner's rule
			hash = (hash * base + (int) config.charAt(i)) % size;
		}
		
		
		
		return Math.abs(hash);
	}
	/*
	 * Adds record to the dictionary. This method must throw a DictionaryException if record.getConfiguration() is already in the dictionary.
	 */
	
	public int put(Data record) throws DictionaryException {
		try
		{
		String config = record.getConfiguration();
	    int index = hash(record.getConfiguration()); // gives it a spot on the hash table
	    
	    if (table[index].isEmpty())
	    {
	    	table[index].add(record);
			numRecords++;
			return 0;
	    }
	    
	    for(int counter = 0; counter < table[index].size(); counter++) {
	    	if(table[index].get(counter).getConfiguration().equals(record.getConfiguration()))
	    	{
	    		throw new DictionaryException();
	    	}
	    	
	    }
		table[index].add(record);
		numRecords++;
		return 0;
		
	}
	catch (IndexOutOfBoundsException e)
	{
		throw new DictionaryException();
	}
	}
		

/* 
 * Removes the record with the given config from the dictionary. Must throw a DictionaryException if no record in the hash table stores config.
 */
	public void remove(String config) throws DictionaryException {
		try
		{
			
			int index = hash(config);
			if (table[index].isEmpty())
			{
				throw new DictionaryException();
			}
			
			for (int i = 0; i < table[index].size(); i++) 
			{
				if(table[index].get(i).getConfiguration().equals(config))
				{
					table[index].remove(i);
					numRecords--;
					return;
				}
			}
			
			
		}
		catch (IndexOutOfBoundsException ex)
		{
			throw new DictionaryException();
		}
		catch (DictionaryException e)
		{
			throw new DictionaryException();
		}
		
	}

/*
 * Returns the score stored in the record of the dictionary with key config, or -1 if config is not in the dictionary.
 */
	public int get(String config) {
		int index = hash(config);
		
		for (int i = 0; i < table[index].size(); i++)
		{
			if(table[index].get(i).getConfiguration().equals(config))
			{
				return table[index].get(i).getScore();
			}
		}		
		return -1;
		
		
		
	}

/*
 * returns the amount of records
 */
	public int numRecords() {
		
		return numRecords;
	}

	
	
}
