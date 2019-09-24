public class CounterThread extends Thread {

	private int lowerBound, upperBound;
	private int primeCount;
	private long duration;

	public CounterThread(int lowerBound, int upperBound){
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	/**
	 * Evaluates whether or not the given integer, {@code i}, is a prime number.
	 * @param i the number to evaluate
	 * @return {@code true}, if {@code i} is a prime number<br> {@code false}, if otherwise
	 */
	private boolean isPrime(int i){
		if(i == 1 || i == 2){
			//1 and 2 are automatically prime, no need to evaluate that
			return true;
		}

		//if 'i' is prime, return true

		return false;
	}

	@Override
	public void run() {
		long startTime = System.nanoTime();
		primeCount = 0;

		//Loop through range
		for(int i = lowerBound; i <= upperBound; i++){
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
		return (duration / (10.0 * (10.0 * 8.0)));
	}
}
