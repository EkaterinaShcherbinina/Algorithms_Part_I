package UnionFind.Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CALC_CONST = 1.96;
    private double[] xArray;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        xArray = new double[trials];
        for (int i = 0; i < trials; i++) {
            performPercolation(n, i);
        }
    }

    private void performPercolation(int n, int trial) {
        Percolation percolation = new Percolation(n);
        int row;
        int col;

        while (!percolation.percolates()) {
            row = StdRandom.uniform(1, n + 1);
            col = StdRandom.uniform(1, n + 1);

            percolation.open(row, col);
        }

        double number = percolation.numberOfOpenSites();
        xArray[trial] = number / (n * n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (stddev() * CALC_CONST / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (stddev() * CALC_CONST / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {

        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean =                     " + percolationStats.mean());
        System.out.println("stddev =                   " + percolationStats.stddev());
        System.out.println("95% confidence interval =  [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
