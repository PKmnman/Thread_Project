import java.util.ArrayList;

public class Bite {
    //Bite data
    private ArrayList<Integer> work;
    private int totalBites;

    //Creates a Bite object with a List that starts with the number given to it (start_point) till 25000 after it
    public Bite(int size,int start_point){
        work = new ArrayList<>(size);
        for (int i = start_point; i < start_point + size - 1 ; i++) {
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


}
