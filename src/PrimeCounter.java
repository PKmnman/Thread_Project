public class PrimeCounter {
	
	public static void main(String[] args) {

		int totalLowerBound = 0;
		int totalUpperBound = 10000;
		int totalAmtOfNumbers = totalUpperBound - totalLowerBound;
		int threadCount = 3;
		int leftover = 0;
		CounterThread[] threadArray = new CounterThread[threadCount];

		double amtOfNumbers = (double)totalAmtOfNumbers/threadCount;
		leftover = totalAmtOfNumbers % threadCount;
		int currentNumber = 0;

		for (int i = 0; i < threadCount; i++) {
			if ((i == threadCount-1) && leftover != 0) {
				int start = currentNumber;
				threadArray[i] = new CounterThread(start, totalUpperBound);
			} else {
				int start = currentNumber;
				currentNumber += (int)amtOfNumbers;
				threadArray[i] = new CounterThread(start, currentNumber);
				currentNumber += 1;
			}
		}

		for (int i = 0; i < threadCount; i++) {
			threadArray[i].start();
		}

		try {
			for (int i = 0; i < threadCount; i++) {
				threadArray[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < threadCount; i++) {
			printThreadInfo(threadArray[i], i+1);
			System.out.println();
		}



		CounterThread t1 = new CounterThread(3, 32000);
		CounterThread t2 = new CounterThread(4235609, 7899101);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		printThreadInfo(t1, 1);
		System.out.println();
		printThreadInfo(t2, 2);
		
	}
	
	private static void printThreadInfo(CounterThread thread, int threadNumber) {
		System.out.printf("Thread %d:%n", threadNumber);
		System.out.printf("\tNumber of Primes: %d%n", thread.getPrimeCount());
		System.out.printf("\tDuration: %.4f seconds%n", thread.getDuration());
	}
	
}
