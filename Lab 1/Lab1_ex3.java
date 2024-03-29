public class Lab1_ex3 {
    public static void main(String[] args) {

        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < 10; i++) {
            threads[i] =  new MyThread(i + 1);
            threads[i].start();
        }
        
        
        for (int i = 0; i < numThreads; ++i) {
            try {
                threads[i].join();
            }
             catch (InterruptedException e) {}
        }

        System.out.println("In main: threads all done");
    }
}

class MyThread extends Thread {
    private int number;

    public MyThread(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            int result = number * i;
            System.out.println(number + " * " + i + " = " + result);
        }
    }
}