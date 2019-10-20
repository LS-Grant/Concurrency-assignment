//The Main class to run all threads.

/**
 * @author Luc Saunders-Grant
 * @version 1.0
 */
public class Main 
{

  private int n = 400;
  private int m = 4;
  private Data data = new Data(m);
  
  public static void main(String[] args) throws InterruptedException 
  {
    new Main().startProgram();
  }
  
  private void startProgram() throws InterruptedException 
  {
    Thread a = new ThreadA(n, data);
    Thread b = new ThreadB(n, data);
    Thread c = new ThreadC(n, data);

    a.start();
    b.start();
    c.start();
    a.join();
    b.join();
    c.join();
  }
}
