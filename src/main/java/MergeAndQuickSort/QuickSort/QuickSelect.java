package MergeAndQuickSort.QuickSort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSelect {
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while(true) {
            while(a[lo] > a[++i])
                if(i == hi) break;

            while(a[lo] < a[--j])
                if(lo == j) break;

            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static int select(int[] a,  int key) {
        //StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;


        while(lo < hi) {
            int j = partition(a, lo, hi);

            if(j == key) return a[j];
            else if(j < key) {
                lo = j + 1;
            }
            else {
                hi = j - 1;
            }
        }
        return  a[key];
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {0, 7, 1, 9, 6, 17, 43, 20, 8, 4};

        int index = QuickSelect.select(a, 3);
        System.out.println(index);
    }
}
