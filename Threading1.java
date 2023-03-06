public class Threading1 {
    public static void main(String[] args) {
		
		MyThread1 thread1 = new MyThread1();    
		MyThread2 thread2 = new MyThread2();
        MyThreadInner innerThread = new MyThreadInner();

		thread1.start();
        thread2.start();
        innerThread.start();

    
	}

    
    private static class MyThreadInner extends Thread {

        public void run() {
            for(int i =0; i<3; i++){
                System.out.println("Inner Thread : " + i);
            }
       }
    }
    
    
}

class MyThread1 extends Thread {

    /* thread code */
    public void run() {

        for(int i =0; i<3; i++){
            System.out.println("Mythread1 : " + i);
        }

    } 

}

class MyThread2 extends Thread {
    /* thread code */
    public void run() {
        for(int i =0; i<3; i++){
            System.out.println("Mythread2 : " + i);
    } 

    }}
