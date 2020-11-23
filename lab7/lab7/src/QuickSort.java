@SuppressWarnings("unchecked")

public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] table) {
        // Sort the table of data using Quick Sort from index 0 to index table.length-1
        // (i.e. sort the entire table)

        quicksort(table, 0, table.length - 1);

    }

    private static <T extends Comparable<T>> void quicksort(T[] table, int first, int last) {

        // Sort the table using quicksort from index first to index last
        // Precondition: first <= last

        // Base case: If the given section of the table has 1 element,
        // then it is sorted already, so just return
        if (first == last){
            return;
        }

        if (first < last) {

            // Partition the table so that the table contains all elements less than the pivot,
            // followed by the pivot, followed by all elements greater than the pivot.
            // Return the final position of the pivot after partioning.
            int pivotIndex = partition(table, first, last);

            // Sort the elements less than the pivot
            quicksort(table, first, pivotIndex - 1);

            // Sort the elements greater than the pivot
            quicksort(table, pivotIndex + 1, last);

        }
    }

    private static <T extends Comparable<T>> int partition(T[] table, int first, int last) {
        // COMPLETE THIS METHOD BASED ON THE COMMENTS MARKED WITH ***

        // Select the first element in the table as the pivot
        T pivot = table[first];

        int left = first;
        int right = last;
        while (left < right) {
            // 从右往左扫描，寻找比枢轴元素小的，并填入坑中
            while (left < right && table[right].compareTo(pivot)>=0) {
                right--;
            }
            if (left < right) {
                table[left++] = table[right];
            }
            // 从左往右扫描，寻找比枢轴元素大的，并填入新坑中
            while (left < right && table[left].compareTo(pivot) < 0) {
                left++;
            }
            if (left < right) {
                table[right--] = table[left];
            }
        }
        table[left] = pivot;
        return left;

        // Now the table consists of:
        // the pivot
        // followed by the first partition containing elements <= pivot (not necessarily sorted)
        // followed by the second partition containing elements > pivot (not necessarily sorted)
        // Swap the pivot with the final value in the first partition ***


        // Return the final position of the pivot  ***


    }

    private static <T extends Comparable<T>> void swap(T[] table, int i, int j) {
        // Swaps table[i] with table[j]
        T temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    public static void main(String[] args) {

        // Create a random table of 20 integers with values between 10 and 99
        int num = 50;
        Integer[] table = new Integer[num];
        for (int i = 0; i < num; i++)
            table[i] = (int) (Math.random() * 90) + 10;

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
        System.out.println("快排时间:"+(end-start));

    }


}
