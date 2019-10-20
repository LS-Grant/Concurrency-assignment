//A class used to generate n random numbers and stores them in m shared memory

/**
 * @author Luc Saunders-Grant
 *
 */
public class ThreadA extends Thread 
{
  private int n;
  public final Data data;
  
  //This constructs Thread A
  /**
   * @param n the amount of data that is going to be generated
   * @param data the actual data that is stored in the shared memory
   */
  public ThreadA(int n, Data data) 
  {
	 this.n = n;
	 this.data = data;
  }

  public void run() 
  {
	  	//This loop generates n numbers and then populates the shared memory
	    for (int i = 0; i < n; i++) 
	    {
	    int val = getRandomNumberInRange(0, n);
	      while (data.isFull()) 
	      {
	        try 
	        {
	          Thread.sleep(1);
	        } 
	        catch (InterruptedException e) 
	        {
	          e.printStackTrace();
	          return;
	        }
	      }
	      synchronized (data) 
	      {
	        data.listOfNumbers.add(val);
	      }
	      System.out.println(i + " Number " + val + " Generated");
	    }
	    data.setCompleted();
	  }
  
  	//Generates a number within the range of 0 to n, n can be changed to incorporate a higher amount of integers
	/**
	 * @param min Minimum value of the number range
	 * @param max Maximum value of the number range
	 * @return returns a random number in the range set 
	 */
	private static int getRandomNumberInRange(int min, int max) 
	{

		return (int)(Math.random() * ((max - min) + 1)) + min;
	}

}
