//Class which creates the shared memory for all threads to access
import java.util.ArrayList;

/**
 * @author Luc Saunders-Grant
 *
 */
public class Data 
{
	//Creates ArrayList to store generated numbers.
	public ArrayList<Integer> listOfNumbers = new ArrayList<>();
	private int m;
	private boolean completed = false;

	/**
	 * @param m The maximum size of this shared data
	 */
	public Data(int m) 
	{
		this.m = m;
	}
	
	// This method is called when the A thread is finished populating the shared memory array list
	public synchronized void setCompleted() 
	{
	  completed = true;
	}
 
	/**
	 * @return Check to see if the thread is finished
	 */
	public synchronized boolean isCompleted() 
	{
		return completed;
	}

	/**
	 * @return Checks to see if the array list has reached it's set limit
	 */
	public synchronized boolean isFull() 
	{
	    return listOfNumbers.size() >= m;
	}
}
