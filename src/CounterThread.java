
public class CounterThread extends Thread {

	private long lowerBound, upperBound;
	private int primeCount;
	private long duration;

	public CounterThread(long lowerBound, long upperBound){
		super();
		//Min-Max calls make sure the lower bound is the smallest and the upper bound
		//is the largest of the two values.
		this.lowerBound = Math.min(lowerBound, upperBound);
		this.upperBound = Math.max(upperBound, lowerBound);
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
	
	@Override
	public synchronized void start() {
		duration = 0;
		primeCount = 0;
		super.start();
	}
	
	@Override
	public void run() {
		long startTime = System.nanoTime();

		//Loop through range
		for(long i = lowerBound; i <= upperBound - 1; i++){
			//if 'i' is a prime number
			if(isPrime(i)){
				primeCount++;
			}
		}

		long endTime = System.nanoTime();
		duration = endTime - startTime;
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
	
	@Override
	public String toString() {
		return String.format("Thread %s:%n\tNum of Primes: %d%n\tDuration: %.4f seconds%n", this.getName(), this.getPrimeCount(), this.getDuration());
	}
	
}
