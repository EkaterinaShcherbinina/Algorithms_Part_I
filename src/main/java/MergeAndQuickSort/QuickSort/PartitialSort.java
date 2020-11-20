package MergeAndQuickSort.QuickSort;

public class PartitialSort {
    public static void sort(int[] a, int lo, int hi) {
        if(hi <= lo) return;

        int lt = lo, gt = hi;
        int i = lo;
        int v = a[lo];

        while(i <= gt) {
            if(a[i] < v) exch(a, lt++, i++);
            else if (a[i] > v) exch(a, i, gt--);
            else i++;
        }

        for(int j = 0; j < a.length; j++) {
            System.out.print(" " + a[j]);
        }
         return;

       /* sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);*/
    }

    private static void exch(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 4, 9, 7, 3, 2, 3, 3, 6, 3, 1};

        PartitialSort.sort(array, 0, array.length - 1);
    }
}
