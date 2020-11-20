package MergeAndQuickSort.MergeSort.Merge;

public class MergeSort {
    private static void sort(Comparable a[], Comparable aux[], int low, int high) {
        if(high <= low) return;

        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);
        merge(a, aux, low, high, mid);
    }

    public static void sort(Comparable a[]) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid) {

        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    public static void main(String[] args) {
        Integer[] array = {3, 5, 3, 7, 8, 55, 7, 33, 47, 14, 90, 0, 59, 21, 73, 56, 72, 84, 30, 79, 2, 66};
        MergeSort.sort(array);
        for(Integer val: array) {
            System.out.print(val + " ");
        }
    }
}
