import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class SharedCounterArrayMainLock {

    public static void main(String[] args) {
		int end = 1000;
    	int[] array = new int[end];
    	int numThreads = 4;
		Lock lock = new ReentrantLock();

		CounterThread threads[] = new CounterThread[numThreads];
		
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread(end, array, numThreads, lock);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
		check_array (end, array, numThreads);
    }
     
    static void check_array (int end, int[] array, int numThreads)  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array[i] != numThreads*i) {
				errors++;
				System.out.printf("%d: %d should be %d\n", i, array[i], numThreads*i);
			}         
        }
        System.out.println (errors+" errors.");
    }


    static class CounterThread extends Thread {
		int end;
		int[] array;
		int numThreads;
		Lock lock;
       public CounterThread(int end,int[] array,int numThreads, Lock lock) {
		this.end = end;
		this.array = array;
		this.numThreads= numThreads;
		this.lock = lock;
       }
  	
       public void run() {
  
            for (int i = 0; i < end; i++) {
				for (int j = 0; j < i; j++){
					lock.lock();
					try{
						array[i]++;
					}finally {    
						lock.unlock();
					 } 
				}	
            } 
		}            	
    }
}
  
