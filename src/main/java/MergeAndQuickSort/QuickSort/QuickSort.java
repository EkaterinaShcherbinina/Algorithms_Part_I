package MergeAndQuickSort.QuickSort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class QuickSort {
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while(true) {

            while(less(a[++i], a[lo]))
                if(i == hi) break;

            while(less(a[lo], a[--j]))
                if(j == lo) break;

            if(i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int j = partition(a, lo, hi);

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] array = new Integer[15];
        for(int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        for (Integer val: array) {
            System.out.println(val);
        }

        System.out.println();
        QuickSort qs = new QuickSort();
        qs.sort(array);

        for (Integer val: array) {
            System.out.println(val);
        }

    }
}
