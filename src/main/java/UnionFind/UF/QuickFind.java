package UnionFind.UF;

public class QuickFind {
    private int[] array;

    public QuickFind(int n) {
        array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return array[p] == array[q];
    }

    public void union(int p, int q) {
        int pid = array[p];
        int qid = array[q];
        if(!connected(p, q)) {
            for(int i = 0; i < array.length; i++) {
                if(array[i] == pid) array[i] = qid;
            }
        }
    }

    public void print() {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
