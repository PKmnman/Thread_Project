import java.util.ArrayList;

public class Bite {
    //Bite data
    private int SIZE = 25000;
    private ArrayList work = new ArrayList(SIZE);

    //Creates a Bite object with a List that starts with the number given to it (x) till 25000 after it
    public Bite(int x){
        for (int i = x; i < x + SIZE - 1 ; i++) {
            work.add(i);
        }
    }
    //Creates and return a Bite interval
    public Bite getWork(int x){
        Bite workBite = new Bite(x);
        return workBite;
    }


}
