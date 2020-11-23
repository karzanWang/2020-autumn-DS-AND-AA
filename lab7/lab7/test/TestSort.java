/**
 * @author : wqruan
 * @version : 1.0.0
 * @date : 2020/11/15 16:01
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.BeforeTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;


public class TestSort {
    private static final int size = 100000;  // Test data size, you find the best algorithm for different dataset sizes.
    private static Integer[] testSet = new Integer[size];
    private static Integer[] verifySet = new Integer[size];

    @BeforeAll
    static void generateTestSet() {
        for (int i = 0; i < testSet.length; i++) {
            int tmp = (int) (Math.random() * 1000000);
            testSet[i] = tmp;
            verifySet[i] = tmp;
        }
        Arrays.sort(verifySet);

    }

    @Test
    public void testMergeSort() {
        Integer[] test1 = testSet.clone();
        MergeSort.sort(test1);
        assertArrayEquals(verifySet, test1);
    }

    @Test
    public void testQuickSort() {
        Integer[] test1 = testSet.clone();
        QuickSort.sort(test1);
        assertArrayEquals(verifySet, test1);
    }

    @Test
    public void testInsertSort() {
        Integer[] test1 = testSet.clone();
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(test1[i]);
//        }
        InsertSort.sort(test1);
        assertArrayEquals(verifySet, test1);
    }
}

