package lab_three;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventSimulator {
    private final List<Cow> arrivalLine;
    private final MyHeap heap;

    /**
     * init
     *
     * @param k           k cows
     * @param arrivalLine arrival time, eating time of cows
     */
    public EventSimulator(int k, List<Cow> arrivalLine) {
        if (arrivalLine == null) {
            throw new NullPointerException("arrival line should not be null");
        }
        this.heap = new MyHeap(k);
        this.arrivalLine = arrivalLine;
    }

    /**
     * simulate the process
     *
     * @return string of time, arrival, eating and leave
     * @throws HeapOverflowException too many items
     * @throws HeapEmptyException    no item
     */
    public String simulate() throws HeapOverflowException, HeapEmptyException {
        int parsing = 0;
        int capacity = this.heap.getCapacity();
        StringBuilder sb = new StringBuilder();
        for (Cow cow : this.arrivalLine) {
            parsing += 1;
            int arrival = cow.getArrival(), eating = cow.getEating();
            int ready = (parsing > capacity) ? this.heap.deleteMin() : 0;
            int leave = Math.max(ready, arrival) + eating;
            this.heap.insert(leave);
            sb.append(String.format("Time: %8d, arrival: %8d, stay: %8d, leave: %8d\n", ready, arrival, eating, leave));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws HeapOverflowException, HeapEmptyException, IOException {
        List<Cow> arrivalLine = new ArrayList<>();
        Random r = new Random();
        int prev_arrival = 0;
        for (int i = 0; i < 1000000; i++) {
            int arrival = prev_arrival + r.nextInt(10);
            int eating = r.nextInt(10);
            prev_arrival = arrival;
            arrivalLine.add(new Cow(arrival, eating));
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("res" + File.separator + "list.obj"));
        oos.writeObject(arrivalLine);
        oos.close();

        EventSimulator es = new EventSimulator(3000, arrivalLine);
        String s = es.simulate();
        ObjectOutputStream nOos = new ObjectOutputStream(new FileOutputStream("res" + File.separator + "res.obj"));
        nOos.writeObject(s);
        nOos.close();
    }
}
