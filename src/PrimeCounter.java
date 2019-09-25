public class PrimeCounter {
	
	public static void main(String[] args) {
		
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
