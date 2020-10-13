package lab_three;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventSimulator {
    MyHeap myHeap;
    List<Cow> cows;
    int time;
    int k;
    /**
     * todo: init
     *
     * @param k           k cows
     * @param arrivalLine arrival time, eating time of cows
     */
    public EventSimulator(int k, List<Cow> arrivalLine) {
         myHeap = new MyHeap(k);
         cows = arrivalLine;
         time = 0;
         this.k=k;
    }

    /**
     * todo: simulate the process
     *
     * @return string of time, arrival, eating and leave
     * @throws HeapOverflowException too many items
     * @throws HeapEmptyException    no item
     */
    public String simulate() throws HeapOverflowException, HeapEmptyException {
        StringBuilder  stringBuilder = new StringBuilder();
        String result = "";
        int sz = cows.size();
        for(int j =0;j< k;j++){
            int temp = (time<cows.get(j).getArrival()?cows.get(j).getArrival():time)+cows.get(j).getEating();
            myHeap.insert(temp);
            stringBuilder.append(String.format("Time:%9d, arrival:%9d, stay:%9d, leave:%9d\n",time,cows.get(j).getArrival(),cows.get(j).getEating(),temp));
        }
        for (int i = k; i < sz;i++) {
                time = myHeap.findMin();
                myHeap.deleteMin();
                int temp = (time<cows.get(i).getArrival()?cows.get(i).getArrival():time)+cows.get(i).getEating();
                myHeap.insert(temp);
            stringBuilder.append(String.format("Time:%9d, arrival:%9d, stay:%9d, leave:%9d\n",time,cows.get(i).getArrival(),cows.get(i).getEating(),temp));
        }

        return stringBuilder.toString();
    }

}
