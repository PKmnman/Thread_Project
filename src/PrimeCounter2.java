import java.util.Scanner;

public class PrimeCounter2 extends Thread{
    public static void main(String[] args) {

        // Asks the user the amount of threads they want to use
        System.out.print("How many threads would you like to create? : ");
        Scanner in = new Scanner(System.in);
        int threadNum = in.nextInt();

        // Asks the user the number they want to count primes up to
        System.out.print("\nWhat number do you want to count primes up to? :");
        long numOfPrimeHigh = in.nextLong();
        System.out.println();

        // Asks the user the size of the Bite
        System.out.println("\nHow big do you want the bite to be: ");
        int biteSize = in.nextInt();
        System.out.println();


        long totalUpperBound = numOfPrimeHigh;
        int threadCount = threadNum;
        Thread[] threadArray = new Thread[threadCount];


        int biteInterval = ((int)totalUpperBound / biteSize);






    }
}
