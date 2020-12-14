package lab_nine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author : wqruan
 * @version : 1.0.0
 * @date : 2020/12/7 10:37
 */
public class KMP {

    public static ArrayList<Integer> search(String str, String pattern) {
        // TODO: implement kmp search here. One str could contain multiple patterns, please output all patterns' positions
        int n = str.length();
        ArrayList<Integer> res = new ArrayList<>();
        int m = pattern.length();
        int[] arr = lps(pattern);
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && pattern.charAt(q) != str.charAt(i)) {
                q = arr[q];
            }
            if (pattern.charAt(q) == str.charAt(i)) {
                q = q + 1;
            }
            if (q == m) {
                res.add(i+1 - m);
                q = arr[q-1];
            }
        }
        return res;
    }

    private static int[] lps(String pattern) {
        // TODO: construct jump table here
        int m = pattern.length();
        int[] array = new int[m];
        array[0] = 0;
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = array[k];
            }
            if (pattern.charAt(k) == pattern.charAt(q)) {
                k = k + 1;
            }
            array[q] = k;
        }

        return array;
    }


    public static void main(String[] args) {
        KMP kmp = new KMP();


        String str = "abxabcabyabyab";
        String pattern = "abyab";
        for (int tmp : search(str, pattern)) {
            System.out.println(tmp);
        }

    }
}
