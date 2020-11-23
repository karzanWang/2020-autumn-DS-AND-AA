@SuppressWarnings("unchecked")

public class MergeSort {

	public static <T extends Comparable<T>> void sort(T[] table)
	{
		// Sort the table of data using Merge Sort

		// Base case: If the table is length 1, then it is sorted already, so just return
		if(table.length == 1){
			return;
		}

		if (table.length > 1) {
			// Split the table into two "halves"
			int halfSize = table.length/2;
			T[] leftHalf = (T[]) new Comparable[halfSize];
			T[] rightHalf = (T[]) new Comparable[table.length - halfSize];
			System.arraycopy(table, 0, leftHalf, 0, halfSize);
			System.arraycopy(table, halfSize, rightHalf, 0, table.length - halfSize);

			// Sort the left half (recursively)
			sort(leftHalf);

			// Sort the right half (recursively)
			sort(rightHalf);

			// Merge the left half and the right half back into the original table
			merge(table, leftHalf, rightHalf);
		}
	}

	private static <T extends Comparable<T>> void merge(T[] finalTable, T[] leftHalf, T[] rightHalf) 
	{
		// COMPLETE THIS METHOD BASED ON THE COMMENTS MARKED WITH ***

		int i = 0;	// index of next position to consider in leftHalf
		int j = 0;	// index of next position to consider in rightHalf
		int k = 0;	// index of next position to fill in finalTable

		// While we haven't copied all of the elements from one of the halves into the finalTable: ***
		while(i<leftHalf.length && j<rightHalf.length){
			if (leftHalf[i].compareTo(rightHalf[j])>=0){
				finalTable[k++] = rightHalf[j++];
			}else if(leftHalf[i].compareTo(rightHalf[j])<=0){
				finalTable[k++] = leftHalf[i++];
			}
		}

		// While there are still elements in the left half, copy these into the remaining positions
		// of the final table.***
		while(i <= leftHalf.length-1){
			finalTable[k++] = leftHalf[i++];
		}


		// While there are still elements in the right half, copy these into the remaining positions
		// of the final table.***
		while(j <= rightHalf.length-1){
			finalTable[k++] = rightHalf[j++];
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
		System.out.println("归并时间:"+(end-start));

	}


}
