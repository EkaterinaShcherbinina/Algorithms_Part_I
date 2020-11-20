package UnionFind.Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] percolation;
    private final int size;
    private int openedCount = 0;

    private final WeightedQuickUnionUF weightedQuickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        percolation = new boolean[n][n];

        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        size = n;

        initialUnion(0, 1, n);
        initialUnion(n * n + 1, n * n + 1 - n, n * n);
    }

    private void initialUnion(int root, int start, int end) {
        for (int i = start; i <= end; i++) {
            weightedQuickUnionUF.union(root, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();

        int rowPercolation = row - 1;
        int colPercolation = col - 1;

        percolation[rowPercolation][colPercolation] = true;
        openedCount++;

        int openedIndex = index(rowPercolation, colPercolation);

        if (rowPercolation - 1 >= 0 && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(openedIndex, index(rowPercolation - 1, colPercolation));
        }
        if (colPercolation - 1 >= 0 && isOpen(row, col - 1)) {
            weightedQuickUnionUF.union(openedIndex, index(rowPercolation, colPercolation - 1));
        }
        if (rowPercolation + 1 < size && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(openedIndex, index(rowPercolation + 1, colPercolation));
        }
        if (colPercolation + 1 < size && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(openedIndex, index(rowPercolation, colPercolation + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
        return percolation[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) throw new IllegalArgumentException();
        if (!isOpen(row, col)) return false;
        return weightedQuickUnionUF.find(index(row - 1, col - 1)) == weightedQuickUnionUF.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return openedCount > 0 && weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(size * size + 1);
    }

    private int index(int row, int col) {
        return row * size + col + 1;
    }
}
