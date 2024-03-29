public class Lab1_ex2 {
    public static void main(String[] args) {

        
        Thread[] threads1 = new Thread[5];
        Thread[] threads2 = new Thread[5];

        /* create and start threads */
        for (int i = 0; i < 5; ++i) {
            threads1[i] = new MyThread1();
            threads2[i] = new MyThread2();
            threads1[i].start();
            threads2[i].start();
        }
        

        for (int i = 0; i < 5; ++i) {
            try {
                threads1[i].join();
                threads2[i].join();
            }
             catch (InterruptedException e) {}
        }

        System.out.println("All threads have finished executing");
    }
}

class MyThread1 extends Thread {
    public void run() {
        System.out.println("Hello from thread 1");
    }
}

class MyThread2 extends Thread {
    public void run() {
        System.out.println("Hello from thread 2");
    } 
}