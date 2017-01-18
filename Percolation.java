
////////////////////////////////////////////////////////////////////
// Percolation:
// Implements a Percolation class using WeightedQuickUnionUF class.
// Assignment from Princeton's algorithms Course taken via Coursera.
// David Riff - 2017
////////////////////////////////////////////////////////////////////

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF id, checkTop;
    private int size;
    private int top = 0;
    private int bottom;
    private int open = 0;
    private boolean[][] status;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Please enter a value bigger than 0");
        size = n;

        id = new WeightedQuickUnionUF(size * size + 2);
        checkTop = new WeightedQuickUnionUF(size * size + 1);
        bottom = size * size + 1;

        status = new boolean[size][size];
    }

    // receives a two dimensional coordinate and returns the linear coordinate
    private int toLinear(int row, int col) {
        return ((row - 1) * this.size + col);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > this.size || col <= 0 || col > this.size) {
            throw new IndexOutOfBoundsException("Please enter a number in [1, " + this.size + "]");
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (!isOpen(row, col)) {
            this.status[row - 1][col - 1] = true;
            this.open++;

            if (row == 1) {
                this.id.union(top, toLinear(row, col));
                this.checkTop.union(top, toLinear(row, col));
            }
            if (row == size)
                id.union(bottom, toLinear(row, col));

            if (row > 1 && isOpen(row - 1, col)) {
                this.id.union(toLinear(row - 1, col), toLinear(row, col));
                this.checkTop.union(toLinear(row - 1, col), toLinear(row, col));
            }
            if (row < size && isOpen(row + 1, col)) {
                this.id.union(toLinear(row + 1, col), toLinear(row, col));
                this.checkTop.union(toLinear(row + 1, col), toLinear(row, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                this.id.union(toLinear(row, col - 1), toLinear(row, col));
                this.checkTop.union(toLinear(row, col - 1), toLinear(row, col));
            }
            if (col < size && isOpen(row, col + 1)) {
                this.id.union(toLinear(row, col + 1), toLinear(row, col));
                this.checkTop.union(toLinear(row, col + 1), toLinear(row, col));
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        return status[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return this.checkTop.connected(top, toLinear(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.open;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.id.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation pc = new Percolation(6);
        pc.open(1, 6);
        System.out.println(pc.isFull(1, 6));
    }

}