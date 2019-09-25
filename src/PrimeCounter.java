public class PrimeCounter {
	
	public static void main(String[] args) {

		int totalLowerBound = 0;
		int totalUpperBound = 10000;
		int totalAmtOfNumbers = totalUpperBound - totalLowerBound;
		int threadCount = 3;
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



		CounterThread t1 = new CounterThread(3, 32000);
		CounterThread t2 = new CounterThread(4235609, 7899101);
		
		t1.setName("1");
		t2.setName("2");
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(t1);
		System.out.println(t2);
		
	}
	
}
