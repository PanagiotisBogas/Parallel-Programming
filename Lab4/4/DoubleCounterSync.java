
public class DoubleCounterSync {
    public static void main(String[] args) {
        int numThreads = 4;
        DoubleCounter doubleC = new DoubleCounter();

        DoubleCounterThread threads[] = new DoubleCounterThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
			threads[i] = new DoubleCounterThread(doubleC);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		}
        System.out.println("FINAL! N1 = " + doubleC.getN1() + " N2 = " + doubleC.getN2());

    }
    static class DoubleCounterThread extends Thread {
        DoubleCounter tDoubleC;
		
        public DoubleCounterThread(DoubleCounter doubleC) {
         this.tDoubleC = doubleC;
        }
       
        public void run() {
             for (int i = 0; i < 4; i++) {
                tDoubleC.incN1();
                tDoubleC.incN2();
                System.out.println("N1 = " + tDoubleC.getN1() + " N2 = " + tDoubleC.getN2());
             } 
         }            	
     }
}

class DoubleCounter {

    int n1;
    int n2;

    Object n1Obj = new Object();
    Object n2Obj = new Object();

    
    public DoubleCounter (){
		this.n1 = 0;
        this.n2 = 0;
    }
    
    public int getN1() {
			return n1;
    }    
    public int getN2() {
        return n2;
}
    
    public void incN1() {
        synchronized(n1Obj){
            n1 = n1 + 1;
        }
			
    } 

    public void incN2() {
        synchronized(n2Obj){
            n2 = n2 + 1;
        }
} 
    
       
}