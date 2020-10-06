package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] record;
    private double size;
    private double T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        double count;
        size = N;
        this.T = T;
        record = new double[T];
        for (int i = 0; i < T; i++) {
            count = 0;
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int randRow = StdRandom.uniform(0, N);
                int randCol = StdRandom.uniform(0, N);
                try {
                    perc.open(randRow, randCol);
                    count++;
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
            record[i] = count / (size * size);
        }
    }

    public double mean() {
        return StdStats.mean(record);
    }

    public double stddev() {
        return StdStats.stddev(record);
    }

    public double confidenceLow() {
        return mean() - 1.96 * (stddev() / (Math.sqrt(T)));
    }

    public double confidenceHigh() {
        return mean() + 1.96 * (stddev() / (Math.sqrt(T)));
    }

    public static void main(String[] args) {
        PercolationFactory perc = new PercolationFactory();
        PercolationFactory percy = new PercolationFactory();

        PercolationStats test = new PercolationStats(2,10000, perc);
        PercolationStats test1 = new PercolationStats(50,5, percy);

        System.out.println(test.mean());
        System.out.println(test.stddev());

        //System.out.println(test1.mean());
        //System.out.println(test1.stddev());
    }
}
