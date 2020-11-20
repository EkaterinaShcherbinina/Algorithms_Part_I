package StackQueues_ElementarySorts.ElementarySorts.ES;

public class SelectionSort {

    public static void sort(Comparable[] array) {
        int min;
        for(int i = 0; i < array.length; i++) {
            min = i;
            for(int j = i + 1; j < array.length; j++) {
                if(less(array[j], array[min])) min = j;
            }
            swap(array, i, min);
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
        SelectionSort.sort(numbers);
        for(Integer val: numbers) {
            System.out.println(val);
        }
    }
}
