public class PrimeCounter {
	
	public static void main(String[] args) {
		
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
