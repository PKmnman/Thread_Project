import java.util.Scanner;
public class PrimeCounter {
	
	public static void main(String[] args) {

		// Asks the user the amount of threads they want to use
		System.out.print("How many threads would you like to create? : ");
		Scanner in = new Scanner(System.in);
		int threadNum = in.nextInt();

		// Asks the user the number they want to count primes from
		System.out.print("\nWhat number do you want to count primes from? :");
		int numOfPrimeLow = in.nextInt();
		System.out.println();

		// Asks the user the number they want to count primes up to
		System.out.print("\nWhat number do you want to count primes up to? :");
		int numOfPrimeHigh = in.nextInt();
		System.out.println();

		int totalLowerBound = numOfPrimeLow;
		int totalUpperBound = numOfPrimeHigh;
		int totalAmtOfNumbers = totalUpperBound - totalLowerBound;
		int threadCount = threadNum;
		int leftover = 0;
		CounterThread[] threadArray = new CounterThread[threadCount];

		// Checks if the division of numbers between the threads is not a whole number
		double amtOfNumbers = (double)totalAmtOfNumbers/threadCount;
		leftover = totalAmtOfNumbers % threadCount;
		int currentNumber = 0;

		for (int i = 0; i < threadCount; i++) {
			if ((i == threadCount-1) && leftover != 0) { //For last thread with leftover numbers
				int start = currentNumber; //lowerbound
				threadArray[i] = new CounterThread(start, totalUpperBound);
			} else { //Not the last thread or has no leftover numbers
				int start = currentNumber; //lowerbound
				currentNumber += (int)amtOfNumbers; //upperbound
				threadArray[i] = new CounterThread(start, currentNumber);
				currentNumber += 1; //Ensures no overlapping of numbers
			}
		}

		//Sets names of each thread and starts threads
		for (int i = 0; i < threadCount; i++) {
			threadArray[i].setName((i+1) + "");
			threadArray[i].start();
		}

		//Joins threads
		try {
			for (int i = 0; i < threadCount; i++) {
				threadArray[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Prints threads
		for (int i = 0; i < threadCount; i++) {
			System.out.println((threadArray[i]));
		}

	}
	
}
