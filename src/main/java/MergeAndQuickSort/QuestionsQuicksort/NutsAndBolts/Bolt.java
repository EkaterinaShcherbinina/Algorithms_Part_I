package MergeAndQuickSort.QuestionsQuicksort.NutsAndBolts;

public class Bolt implements Comparable<Nut>, NutsBolts {
    private int size;
    private NutOrBolt entity;

    public Bolt(int size, NutOrBolt entity) {
        this.size = size;
        this.entity = entity;
    }

    public String toString() {
        return "Bolt: " + size;
    }

    @Override
    public int compareTo(Nut nut) {
        if (this.size < nut.size()) return -1;
        else if (this.size > nut.size()) return 1;
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
