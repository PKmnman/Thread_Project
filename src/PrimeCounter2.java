import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class PrimeCounter2 extends Thread{
    
    private static WorkAllocator allocator;
    
    public static void main(String[] args) {

        // Asks the user the amount of threads they want to use
        System.out.print("How many threads would you like to create? : ");
        Scanner in = new Scanner(System.in);
        int threadNum = in.nextInt();
    
        System.out.print("\nWhat number do you want to count primes from? :");
        long numOfPrimeLow = in.nextLong();
        System.out.println();
        
        // Asks the user the number they want to count primes up to
        System.out.print("\nWhat number do you want to count primes up to? :");
        long numOfPrimeHigh = in.nextLong();
        System.out.println();
        
        // Asks the user the size of the Bite
        System.out.println("\nHow big do you want the bite to be: ");
        int biteSize = in.nextInt();
        System.out.println();

        //Initialize WorkAllocator
        allocator = new WorkAllocator(numOfPrimeLow, numOfPrimeHigh, biteSize, threadNum);
        
        //Initialize the worker threads
        WorkerThread[] threadArray = new WorkerThread[threadNum];
        
        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new WorkerThread(allocator);
        }
        
        //Store start time
        long startTime = System.nanoTime();
        
        //Start the workers
        for (int i = 0; i < threadArray.length; i++){
            threadArray[i].start();
        }
        
        //Wait for all of the workers to finish using CyclicBarrier
        try {
            allocator.getBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    
        //Store end time in seconds
        double totalDuration = (System.nanoTime() - startTime) / 10e8;

        //Output results
        //TODO: Output the results of the counter


        int totalPrimes = 0;
        for (int i = 0; i < threadArray.length; i++) {
            totalPrimes += threadArray[i].getPrimeCount();
            System.out.println(threadArray[i]);
            System.out.println("Total Amount of Primes are : " + totalPrimes);
        }
    }
}
