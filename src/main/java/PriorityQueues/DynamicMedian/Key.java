package PriorityQueues.DynamicMedian;

public class Key implements Comparable<Key>{
    int median;

    public Key(int median) {
        this.median = median;
    }

    @Override
    public int compareTo(Key o) {
        if(this.median < o.median) return -1;
        else if(this.median > o.median) return 1;
        else return 0;
    }

    public String toString() {
        return String.valueOf(median);
    }
}
