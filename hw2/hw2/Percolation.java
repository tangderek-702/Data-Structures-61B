package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    boolean[] percArray1d; //false- closed, true- open
    WeightedQuickUnionUF percGraph;
    int size;
    int count;
    int virtualTop;
    int virtualBot;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = N;
        this.count = 0;
        percArray1d = new boolean[N * N + 2];
        this.virtualBot = N * N;
        this.virtualTop = N * N + 1;
        this.percGraph = new WeightedQuickUnionUF(N * N + 2);
        virtualConnection();

    }

    private void virtualConnection() {
        //connect virtual top
        for (int i = 0; i < size; i++)
            percGraph.union(virtualTop, i);

        //connect virtual bottom
        for (int i = size * size - size; i < size * size; i++)
            percGraph.union(virtualBot, i);
    }

    private void indexCheck(int row, int col) {
        if(row > size || row < 0 || col > size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int indexTranslate(int row, int col) {
        return row * size + col;
    }


    public void open(int row, int col) {
        int index = indexTranslate(row, col);
        int topIndex = index - size;
        int botIndex = index + size;
        int rightIndex = index + 1;
        int leftIndex = index - 1;
        indexCheck(row, col);

        if (percArray1d[index] == false) {
            percArray1d[index] = true;
            count++;
            //bottom row
            if (row == size) {
                percGraph.union(virtualBot, index);
            }
            //top row
            if (row == 1) {
                percGraph.union(virtualTop, index);
            }
            //Check for adjacent connectivity
            if (row + 1 < size && isOpen(row + 1, col)) {
                percGraph.union(index, indexTranslate(row + 1, col));
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)){
                percGraph.union(index, indexTranslate(row - 1, col));
            }
            if (col + 1 < size && isOpen(row, col + 1)){
                percGraph.union(index, indexTranslate(row, col + 1));
            }
            if (col - 1 >= 0 && isOpen(row, col -1)) {
                percGraph.union(index, indexTranslate(row, col - 1));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isOpen (int row, int col) {
        int index = indexTranslate(row, col);
        if (percArray1d[index] == true) {
            return true;
        }
        return false;
    }

    public  boolean isFull(int row, int col) {
        int index = indexTranslate(row, col);
        if (percGraph.connected(virtualTop, index)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        if (percGraph.connected(virtualTop, virtualBot)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(3);
        test.open(0,0);
        test.open(1,0);
        test.open(2,0);
        test.open(2,2);
        System.out.println(test.isFull(2,2));


        /*
        System.out.println();
        test.open(1,2);
        test.open(2,1);
        System.out.println(test.isFull(2,1));
        System.out.println(test.isFull(0,1));
        System.out.println(test.percolates());
        test.open(1,1);
        System.out.println(test.percolates());
        System.out.println(test.isFull(2,1));
         */
    }

}
