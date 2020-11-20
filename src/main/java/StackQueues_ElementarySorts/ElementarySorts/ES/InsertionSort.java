package StackQueues_ElementarySorts.ElementarySorts.ES;

public class InsertionSort {

    public static void sort(Comparable[] array) {
        for(int i = 1; i < array.length; i++) {
            for(int j = i; j > 0; j--) {
                if(less(array[j], array[j - 1])) {
                    swap(array, j, j - 1);
                } else break;
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    private static void swap(Comparable[] ar, int a, int b) {
        Comparable temp = ar[a];
        ar[a] = ar[b];
        ar[b] = temp;
    }

    public static void main(String[] args) {
        Integer[] numbers = {3, 7, 1, 9, 0, 6, 4, 6, 3};
        InsertionSort.sort(numbers);
        for(Integer val: numbers) {
            System.out.println(val);
        }
    }
}
