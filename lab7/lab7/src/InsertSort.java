/**
 * @author : wqruan
 * @version : 1.0.0
 * @date : 2020/11/15 15:58
 */
public class InsertSort {
    public static <T extends Comparable<T>> void sort(T[] table)
    {
        // Sort the table of data using Insert Sort
        int n = table.length;
        for (int i=1; i<n; ++i)
        {
            T key = table[i];
            int j = i-1;

            while (j>=0 && table[j].compareTo(key)>0)
            {
                table[j+1] = table[j];
                j = j-1;
            }
            table[j+1] = key;
        }

    }
    public static void main(String[] args) {
// Create a random table of 20 integers with values between 10 and 99
        int num = 50;
        Integer[] table = new Integer[num];
        for (int i = 0; i < num; i++)
            table[i] = (int)(Math.random() * 90) + 10;

        // Output original table
        System.out.println("ORIGINAL TABLE:");
        for (Integer i : table)
            System.out.print(i + " ");
        System.out.println();

        // Sort the table
        double start = System.nanoTime();
        sort(table);
        double end = System.nanoTime();

        // Output sorted table
        System.out.println("SORTED TABLE:");
        for (Integer i : table)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("插排时间:"+(end-start));
    }
}
