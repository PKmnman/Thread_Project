import range.Range;

import java.util.concurrent.BrokenBarrierException;

public class WorkerThread extends Thread{
	
	private static int threadID = 1;
	
	private Range<Long> work;
	private WorkAllocator allocator;
	private int primeCount;
	private long duration;
	
	public WorkerThread(WorkAllocator allocator){
		super(allocator.getWorkerGroup(), String.format("Worker %d", threadID++));
		this.allocator = allocator;
	}
	
	@Override
	public void run() {
		long startTime = System.nanoTime();
		
		//While there's work, count primes
		while ((work = allocator.requestWork()) != null){
			//Loop through range
			for (long i = work.low(); i <= work.high(); i++) {
				//if 'i' is a prime number
				if (isPrime(i)) {
					primeCount++;
				}
			}
		}
		
		//Store duration
		duration = (System.nanoTime() - startTime);
		
		//Await on barrier
		try {
			allocator.getBarrier().await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Evaluates whether or not the given integer, {@code i}, is a prime number.
	 * @param n the number to evaluate
	 * @return {@code true}, if {@code i} is a prime number<br> {@code false}, if otherwise
	 */
	private boolean isPrime(Long n){
		if(n >= 1 && n < 3){
			//1 and 2 are automatically prime, no need to evaluate that
			return true;
		}
		if (n % 2 == 0) {
			//Even numbers are never prime
			return false;
		}
		
		//if 'i' is prime, return true
		
		//When 'i' is greater than or equal to 3 take the squareroot of the number then do the remainder operator, if there is no remainder then the number is not a prime so break.
		int num = (int)Math.ceil((Math.sqrt((double)n)));
		for (int j = 3; j <= num ; j++) {
			if (n % j == 0){
				return false;
			}
		}
		return true;
	}
	
	public int getPrimeCount() {
		return primeCount;
	}
	
	/**
	 * Returns the running duration of the {@link CounterThread#run()} method.
	 * @return the running duration of this thread
	 */
	public double getDuration() {
		//Convert to seconds
		return (duration / 10e8);
	}

	public String toString() {
		return String.format("Thread %s:%n\tNum of Primes: %d%n\tDuration: %.4f seconds%n", this.getName(), this.getPrimeCount(), this.getDuration());
	}
	
}
