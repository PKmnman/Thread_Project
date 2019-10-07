import java.util.ArrayList;
// We need to return a range so we can use CounterThread.
// Needs to update the range after each iteration
public class Bite {
    //Bite data
    private ArrayList<Integer> work;
    private int totalBites;
    private boolean used = false;
    private int start;
    private int end;
    private int size = end - start;

    //Creates a Bite object with a List that starts with the number given to it (start_point) till 25000 after it
    public Bite(int start_point,int end_point){
        this.start = start_point;
        this.end = end_point;
        work = new ArrayList<>(size);
        for (int i = this.start; i < this.end ; i++) {
            work.add(i);
        }
        totalBites++;
    }
    //Creates and return a Bite interval
    public Bite getWork(int size,int start_point){
        Bite workBite = new Bite(size,start_point);
        return workBite;
    }
    //Keeps track of how many bites are made
    public int getTotalBites(){
        return totalBites;
    }

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

    public void setUsed(){
        this.used = true;
    }

    public boolean isUsed(){
        return used;
    }



}
