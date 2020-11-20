package MergeAndQuickSort.QuestionsQuicksort.NutsAndBolts;

public class Nut implements Comparable<Bolt>, NutsBolts {
    private int size;
    private NutOrBolt entity;

    public Nut(int size, NutOrBolt entity) {
        this.size = size;
        this.entity = entity;
    }

    public String toString() {
        return "Nut: " + size;
    }

    @Override
    public int compareTo(Bolt bolt) {
        if (this.size < bolt.size()) return -1;
        else if (this.size > bolt.size()) return 1;
        else return 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public NutOrBolt entity() {
        return entity;
    }
}