package UnionFind.UF;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        QuickFind qu = new QuickFind(N);

        for(int i = 0;  i <= 10; i++) {
            int p = in.nextInt();
            int q = in.nextInt();

            if(!qu.connected(p, q)) {
                qu.union(p, q);
            }
        }

        qu.print();
        in.close();
    }
}