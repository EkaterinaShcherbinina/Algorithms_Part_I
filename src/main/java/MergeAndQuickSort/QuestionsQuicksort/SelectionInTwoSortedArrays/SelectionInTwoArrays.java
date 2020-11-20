package MergeAndQuickSort.QuestionsQuicksort.SelectionInTwoSortedArrays;

public class SelectionInTwoArrays {
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

    private static void exch(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static int select(int[] a, int[] b, int key) {
        //StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;

        int mid = lo + (hi - lo) / 2;


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
}
