import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class SharedCounterArrayMainWhileSynchronize {
  
    public static void main(String[] args) {
		int end = 10000;
		int[] array = new int[end];
		int numThreads = 4;
		SharedCounter counter = new SharedCounter();
		Lock lock = new ReentrantLock();

        CounterThread threads[] = new CounterThread[numThreads];
	
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread(counter, array, end, lock);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
        check_array (end, array);
    }
     
    static void check_array (int end, int[] array)  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array[i] != 1) {
				errors++;
				System.out.printf("%d: %d should be 1\n", i, array[i]);
			}         
		}
        System.out.println (errors+" errors.");
    }


    static class CounterThread extends Thread {
		int tEnd;
		int[] tArray ;
		SharedCounter tCounter;
		Lock tLock;
		
  	
       public CounterThread(SharedCounter counter, int[] array, int end, Lock lock) {
			this.tCounter = counter;
			this.tArray = array;
			this.tEnd = end;
			this.tLock = lock;
       }
  	
       public void run() {
       
		while (true) {
			synchronized(tArray){
				if (tCounter.get() >= tEnd) 
					break;
				tArray[tCounter.get()]++;
				tCounter.inc();	
			}				
		} 
	}           	
    }
}
  
class SharedCounter {

    int counter;
    
    public SharedCounter (){
		this.counter = 0;
    }
    
    public int get() {
			return counter;
    }    
    
    public void inc() {
			counter = counter + 1;
    } 
    
       
}
