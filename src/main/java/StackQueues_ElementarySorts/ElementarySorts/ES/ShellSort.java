package StackQueues_ElementarySorts.ElementarySorts.ES;

public class ShellSort {

    public static void sort(Comparable[] array) {
        int n = array.length;
        int h = 1;
        while(h < n / 3)  h = h * 3 + 1;

        while(h >= 1) {
            for(int i = h; i < n; i =+ h) {
                for(int j = i; j > h; j =- h) {
                    if(less(array[j], array[j - h])) swap(array, j, j - h);
                }
            }
            h = h / 3;
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
