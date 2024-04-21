public class NumIntSeq {

    public static void main(String[] args) {

        long numSteps = 10000;
        double sum = 0.0;
        
        /* start timing */
        long startTime = System.currentTimeMillis();

        double step = 1.0 / (double)numSteps;
        /* do computation */
        for (long i=0; i < numSteps; ++i) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
        }
        double pi = sum * step;

        System.out.println("Sum: "+sum);


        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}