package MergeAndQuickSort.MergeSort.CountingInversion;

public class CountInversion {
    private int count = 0;

    public int getCount() {
        return count;
    }

    private void sort(Comparable a[], Comparable aux[], int low, int high) {
        if(high <= low) return;

        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);
        merge(a, aux, low, high, mid);
    }

    public void sort(Comparable a[]) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid) {

        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int sz = hi - mid;
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[i], aux[j])) a[k] = aux[i++];
            else {
                a[k] = aux[j++];
                count += sz - (i - lo);
            }
        }
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    public static void main(String[] args) {
        CountInversion countInversion = new CountInversion();
        Integer[] array = {3, 8, 2, 43, 9, 6, 1, 95};
        countInversion.sort(array);
        System.out.println("Count: " + countInversion.getCount());
        for (Integer val: array) {
            System.out.print(val + " ");
        }
    }
}
