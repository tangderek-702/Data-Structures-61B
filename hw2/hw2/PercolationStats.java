package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] record;
    int size;
    int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        double count;
        size = N;
        this.T = T;
        record = new double[T];
        for (int i = 0; i < T; i++) {
            count = 0;
            Percolation perc = pf.make(N);
            while(!perc.percolates()){
                int randRow = StdRandom.uniform(0, N);
                int randCol = StdRandom.uniform(0, N);
                try {
                    perc.open(randRow,randCol);
                    count++;
                } catch (Exception e) {
                    continue;
                }
            }
            record[i] = count / (size * size);
        }
    }

    public double mean() {
        double sum = 0;
        for(double trial: record) {
            sum = sum + trial;
        }
        return sum / T;
    }

    public double stddev() {
        return StdStats.stddev(record);
    }

    public double confidenceLow() {
        return mean() - 1.96 * (stddev()/(Math.sqrt(T)));
    }

    public double confidenceHigh() {
        return mean() + 1.96 * (stddev()/(Math.sqrt(T)));
    }
}
