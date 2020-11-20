package PriorityQueues.PQ.DynamicMedian;

public class DynamicMedian {
    private MaxPQ maxPQ;
    private MinPQ minPQ;

    public DynamicMedian() {
        maxPQ = new MaxPQ(10);
        minPQ = new MinPQ(10);
    }

    public class MaxPQ<Key extends Comparable<Key>> {
        private Key[] pq;
        private int N;

        public MaxPQ(int capacity) {
            pq = (Key[])new Comparable[capacity];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public Key getMedian() {
            return pq[1];
        }

        public void insert(Key key) {
            if(N == pq.length) resize(pq.length * 2);
            pq[++N] = key;
            swim(N);
        }

        public Key delMax() {
            Key max = pq[1];
            exch(1, N--);
            sink(1);
            pq[N + 1] = null;

            return max;
        }

        private void sink(int k) {
            while(2*k <= N) {
                int j = 2 * k;
                if(j < N && less(j, j + 1)) j++;
                if(less(j, k)) break;

                exch(j, k);
                k = j;
            }
        }

        private void swim(int k) {
            while(k > 1 && less(k/2, k)) {
                exch(k, k/2);
                k = k/2;
            }
        }

        private boolean less(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }

        private void exch(int i, int j) {
            Key temp = pq[i];
            pq[i] = pq[j];
            pq[j] = temp;
        }

        private void resize(int sz) {
            Key[] newQueue = (Key[])new Comparable[sz];
            for(int i = 0; i < pq.length; i++) {
                newQueue[i] = pq[i];
            }
            pq = newQueue;
        }
    }

    public class MinPQ<Key extends Comparable<Key>> {
        private Key[] pq;
        private int N;

        public MinPQ(int capacity) {
            pq = (Key[])new Comparable[capacity];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void insert(Key key) {
            if(N == pq.length) resize(pq.length * 2);
            pq[++N] = key;
            swim(N);
        }

        public Key delMin() {
            Key min = pq[1];
            exch(1, N--);
            sink(1);
            pq[N + 1] = null;

            return min;
        }

        private void sink(int k) {
            while(2*k <= N) {
                int j = 2 * k;
                if(j < N && greater(j, j + 1)) j++;
                if(greater(j, k)) break;

                exch(j, k);
                k = j;
            }
        }

        private void swim(int k) {
            while(k > 1 && greater(k/2, k)) {
                exch(k, k/2);
                k = k/2;
            }
        }

        private boolean greater(int i, int j) {
            return pq[i].compareTo(pq[j]) > 0;
        }

        private void exch(int i, int j) {
            Key temp = pq[i];
            pq[i] = pq[j];
            pq[j] = temp;
        }

        private void resize(int sz) {
            Key[] newQueue = (Key[])new Comparable[sz];
            for(int i = 0; i < pq.length; i++) {
                newQueue[i] = pq[i];
            }
            pq = newQueue;
        }
    }

    public void insert(Key key) {
        if(maxPQ.size() == minPQ.size()) {
            if(maxPQ.isEmpty()) maxPQ.insert(key);
            else if(!less(maxPQ.getMedian(), key)) {
                maxPQ.insert(key);
            } else if(less(maxPQ.getMedian(), key)) {
                minPQ.insert(key);
                Key min = (Key)minPQ.delMin();
                maxPQ.insert(min);
            }
        } else {
            if(less(maxPQ.getMedian(), key)) {
                minPQ.insert(key);
            } else if(!less(maxPQ.getMedian(), key)) {
                maxPQ.insert(key);
                Key max = (Key)maxPQ.delMax();
                minPQ.insert(max);
            }
        }
    }

    public Key delete() throws IllegalAccessException {
        if(maxPQ.isEmpty()) throw new IllegalAccessException();

        Key med = (Key)maxPQ.delMax();
        if(maxPQ.size() < minPQ.size()) {
            Key min = (Key)minPQ.delMin();
            maxPQ.insert(min);
        }
        return med;
    }

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public static void main(String[] args) throws IllegalAccessException {
        DynamicMedian dynamicMedian = new DynamicMedian();
        dynamicMedian.insert(new Key(4));
        dynamicMedian.insert(new Key(6));
        dynamicMedian.insert(new Key(2));
        dynamicMedian.insert(new Key(3));
        dynamicMedian.insert(new Key(5));
        dynamicMedian.insert(new Key(7));
        dynamicMedian.insert(new Key(9));

        Key med = dynamicMedian.delete();
        System.out.println(med);
        Key med2 = dynamicMedian.delete();
        System.out.println(med2);
    }
}
