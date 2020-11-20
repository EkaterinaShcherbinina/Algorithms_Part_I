package UnionFind.SpecificCanonicalElement;

public class QuickUnion {
    private int[] array;
    public QuickUnion(int n) {
        array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int i) {
        while(i != array[i]) {
            i = array[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if(i < j) {
            array[i] = j;
        } else {
            array[j] = i;
        }
    }

    public int find(int i) {
        return root(i);
    }
}
