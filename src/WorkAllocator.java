import range.IntRange;
import range.LongRange;
import range.Range;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class WorkAllocator {
	
	private final long ABS_START;
	private final long ABS_END;
	private final int BYTE_SIZE;
	
	private final int NUM_THREADS;
	
	private long position;
	
	private ReentrantLock requestLock;
	private CyclicBarrier barrier;
	private ThreadGroup workerGroup;
	
	public WorkAllocator(long start, long end, int byte_size, int numOfThreads){
		if(end < start){
			throw new IllegalArgumentException("End value must be greater than start value");
		}
		ABS_START = start;
		ABS_END = end;
		BYTE_SIZE = byte_size;
		NUM_THREADS = numOfThreads;
		
		workerGroup = new ThreadGroup("Worker Threads");
		barrier = new CyclicBarrier(numOfThreads + 1);
		requestLock = new ReentrantLock();
	}
	
	public Range<Long> requestWork(){
		requestLock.lock();
		Range result = null;
		try {
			if(hasWork()){
				//Calculate high end of range
				long high = position + BYTE_SIZE;
				//Check if the range would exceed the set of values being checked
				if(high > ABS_END){
					//Adjust high end of range to fit
					high = high - (high - ABS_END);
				}
				result = new LongRange(position, high);
				position = high;
			}
		}finally {
			requestLock.unlock();
		}
		return result;
	}
	
	public boolean hasWork(){
		requestLock.lock();
		try{
			return position < ABS_END;
		}finally {
			requestLock.unlock();
		}
	}
	
	public synchronized CyclicBarrier getBarrier(){
		return barrier;
	}
	
	public ThreadGroup getWorkerGroup(){
		return workerGroup;
	}
	
}
