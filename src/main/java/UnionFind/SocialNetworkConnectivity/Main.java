package UnionFind.SocialNetworkConnectivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        Scanner scanner = new Scanner(new File("src/com/shcherbinina/input_1000_450000_250000.txt"));
        int earlestTime = 0;
        int p = 0;
        int q = 0;

        long start = System.nanoTime();
        QuickUnion qu = new QuickUnion(scanner.nextInt());
        while (scanner.hasNextInt()) {
            earlestTime = scanner.nextInt();
            p = scanner.nextInt();
            q = scanner.nextInt();
            boolean isTimeFound = qu.union(p, q);

            if(isTimeFound) {
                System.out.println("Earliest time is found for " + p + " and " + q + " members");
                System.out.println("Earliest time is " + earlestTime);
            }
        }
        long finish = System.nanoTime();
        double total =  (finish - start) * Math.pow(10, -9) ;

        System.out.println("Algorithm time is " + total);
    }
}
