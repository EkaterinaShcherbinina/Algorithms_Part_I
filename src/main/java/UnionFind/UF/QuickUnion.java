package UnionFind.UF;

public class QuickUnion {
    private int[] array;
    private int[] sizeArray;

    public QuickUnion(int n) {
        array = new int[n];
        sizeArray = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = i;
            sizeArray[i] = 1;
        }
    }

    public int root(int i) {
        while(i != array[i]) {
            array[i] = array[array[i]];
            i = array[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if(sizeArray[i] < sizeArray[j]) {
            array[i] = j;
            sizeArray[j] += sizeArray[i];
        } else {
            array[j] = i;
            sizeArray[i] += sizeArray[j];
        }
    }

    public void print() {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
