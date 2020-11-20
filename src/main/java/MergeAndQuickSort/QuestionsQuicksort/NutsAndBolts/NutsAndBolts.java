package MergeAndQuickSort.QuestionsQuicksort.NutsAndBolts;

import edu.princeton.cs.algs4.StdRandom;

public class NutsAndBolts {
    private static Nut[] nuts;
    private static Bolt[] bolts;
    public static void sortNutsAndBolts(NutsBolts[] nutsBolts) {
        int size = nutsBolts.length / 2;
        nuts = new Nut[size];
        bolts = new Bolt[size];
        int i = 0, j = 0;
        for (int n = 0; n < nutsBolts.length; n++) {
            if(nutsBolts[n].entity() == NutOrBolt.bolt) bolts[i++] = (Bolt)nutsBolts[n];
            else nuts[j++] = (Nut)nutsBolts[n];
        }
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);
        sort(nuts, bolts, 0, size - 1, bolts[0]);
    }

    public static Nut[] getNuts() {
        return nuts;
    }

    public static Bolt[] getBolts() {
        return bolts;
    }

    public static void sort(Nut[] nuts, Bolt[] bolts, int lo, int hi, Comparable key) {
        if(lo >= hi) return;
        int i = partition(nuts, lo, hi, key);
        Bolt boltKey = (Bolt)key;
        Nut temp = new Nut(boltKey.size(), NutOrBolt.nut);
        partition(bolts, lo, hi, temp);

        sort(nuts, bolts, lo, i, bolts[lo]);
        sort(nuts, bolts, i + 1, hi, bolts[i + 1]);
    }


    private static int partition(Comparable[] a, int lo, int hi, Comparable key) {
        int i = lo - 1;
        int j = hi + 1;

        while(true) {

            while(true) {
                int compare = a[++i].compareTo(key);
                if(compare != -1 || i == hi) break;
            }

            while(true) {
                int compare = key.compareTo(a[--j]);
                if(compare != -1 || j == lo) break;
            }

            if(i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    private static boolean lessOrEqual(Comparable a, Comparable b) {
        return a.compareTo(b) < 0 || a.compareTo(b) == 0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] array, int a, int b) {
        Comparable temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        NutsBolts[] nutsBolts = new NutsBolts[20];
        for(int i = 1, j = 0; i <= 10; j++) {
            Nut nut = new Nut(i++, NutOrBolt.nut);
            nutsBolts[j] = nut;
        }

        for(int i = 1, j = 10; j < 20; j++) {
            Bolt bolt = new Bolt(i++, NutOrBolt.bolt);
            nutsBolts[j] = bolt;
        }

        StdRandom.shuffle(nutsBolts);
        NutsAndBolts.sortNutsAndBolts(nutsBolts);

        Nut[] nuts = NutsAndBolts.getNuts();
        Bolt[] bolts = NutsAndBolts.getBolts();

        for(Nut nut: nuts) {
            System.out.println(" " + nut.toString());
        }

        System.out.println();

        for(Bolt bolt: bolts) {
            System.out.println(" " + bolt.toString());
        }
    }
}
