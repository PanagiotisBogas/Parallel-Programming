public class Lab1_ex1 {

    public static void main(String[] args) {

        MyThread1 firstThread = new MyThread1();
        MyThread2 secondThread = new MyThread2();

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println("Both threads have finished executing");
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