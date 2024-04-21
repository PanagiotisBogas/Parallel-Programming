import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumIntParLock {
    public static double sum;
	public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        long numSteps = 1000000000; 
        sum = 0.0;
        int numThreads = Runtime.getRuntime().availableProcessors();

        // Start timing
        long startTime = System.currentTimeMillis();
        double step = 1.0 / (double)numSteps;

        NumIntParThread[] threads = new NumIntParThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new NumIntParThread(i, numSteps, numThreads, step);
            threads[i].start();
        }

        // Join all threads to wait for their completion
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
            }   

        double pi = sum * step;

        // End timing and print results
        long endTime = System.currentTimeMillis();
        System.out.printf("parallel program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n", pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}


class NumIntParThread extends Thread {
    int tId;
    double tStep;
    int block;
    int start;
	int stop;
    double localSum;

    public NumIntParThread(int i, long numSteps, int numThreads, double step){
        tId = i;
        tStep=step;
        block = (int) (numSteps / numThreads);
        start = i * block;
        stop = start + block;
        if(tId == numThreads-1) stop = (int) numSteps;
    }
    
    public void run(){
        for (long j=start; j < stop; j++) {
            double x = ((double)j+0.5)*tStep;
            localSum += 4.0/(1.0+x*x);
        }
        NumIntParLock.lock.lock();
        try {
            NumIntParLock.sum = NumIntParLock.sum + localSum;
       } finally {
            NumIntParLock.lock.unlock();
       }

    }
}