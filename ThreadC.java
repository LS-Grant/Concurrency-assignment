//This thread reads in the data from the shared data array list and then sorts all the odd numbers into the odd text file
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

/**
 * @author Luc Saunders-Grant
 *
 */
public class ThreadC extends Thread 
{
  public final Data data;
  int n;
  
//This constructs Thread C
  /**
 * @param n the size of the data
 * @param data the data stored in memory
 */
  public ThreadC(int n, Data data) 
  	{
	  this.n = n;
	  this.data = data;
  	}

  	public void run() 
  	{
  		//A String joiner so that the file reads as a list of numbers separated by commas
  		StringJoiner dash = new StringJoiner(" - ");
  		//loops n times and keeps asking if the data is empty hence this is busy waiting
  		while (!data.isCompleted() || !data.listOfNumbers.isEmpty()) 
  		{
  			synchronized (data) 
  			{
  				if (data.listOfNumbers.isEmpty()) 
  				{
  					continue;
  				}
  				//Creates a reference to the value stored in the shared memory
  				Integer value = data.listOfNumbers.get(0);
  				//Checks to see if the value given is odd
  				if (value % 2 > 0) 
  				{
  					dash.add(value.toString());
  					//This line removes the value from the list
  					data.listOfNumbers.remove(value);
  				}
  			}
  		}
  		writeOddFile(dash.toString());
  	}
  	//This method prints the odd values to Odd.txt
  	/**
  	* @param saveToFile this parameter makes it so that we can print the numbers to the file
  	*/
  	private void writeOddFile(String saveToFile) 
  	{
  		System.out.println("Odd Numbers: " + saveToFile);
  		File file = new File("Odd.txt");
  		try (FileWriter fileWriter = new FileWriter(file, false)) 
  		{
  			fileWriter.write(saveToFile);
  		} 
  		catch (IOException e) 
  		{
  			e.printStackTrace();
  		}
  	}
}