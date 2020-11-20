package MergeAndQuickSort.MergeSort.MergingWithSmallerAuxiliaryArray;

import java.util.Comparator;

public class MergeSort {
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        merge(a, aux, 0, n - 1);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        for(int i = 0; i < mid + 1; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(j > hi) a[k] = aux[i++];
            else if(i > mid) a[k] = a[j++];
            else if(less(aux[i], a[j])) a[k] = aux[i++];
            else a[k] = a[j++];
        }
    }
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    public static void main(String[] args) {
        Integer[] array = {3, 5, 9, 10, 14, 16, 1, 4, 6, 7, 13, 17};
        MergeSort.sort(array);
        for(Integer val: array) {
            System.out.print(val + " ");
        }
    }
}
