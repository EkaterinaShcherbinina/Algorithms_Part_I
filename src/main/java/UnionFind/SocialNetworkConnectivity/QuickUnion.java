package UnionFind.SocialNetworkConnectivity;

public class QuickUnion {
    private int[] ids;
    private  int[] sizeTree;

    public QuickUnion(int n) {
        ids = new int[n];
        sizeTree = new int[n];
        for(int i = 0; i < n; i++) {
            ids[i] = i;
            sizeTree[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int i) {
        while(ids[i] != i) {
            ids[i] = ids[ids[i]];
            i = ids[i];
        }
        return i;
    }

    public boolean union(int p, int q) {
        int i = root(p);
        int j = root(q);
        boolean isEarliestTime = false;

        if(i != j) {
            if(sizeTree[i] < sizeTree[j]) {
                ids[i] = j;
                sizeTree[j] += sizeTree[i];
                isEarliestTime = sizeTree[j] == ids.length;
            } else {
                ids[j] = i;
                sizeTree[i] += sizeTree[j];
                isEarliestTime = sizeTree[i] == ids.length;
            }
        }
        return isEarliestTime;
    }
}
