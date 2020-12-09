package bearmaps;

import org.junit.Test;

import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;


public class ArrayHeapMinPQTest {

    @Test
    public void testConstructor() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>(4);
        ArrayHeapMinPQ<Integer> test1 = new ArrayHeapMinPQ<>();
    }

    @Test
    public void testResize() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>(20);
        test.add(101, 1);
        test.add(5, 4);
        test.add(10,2);
        assertEquals(20, test.getArraySize());
    }

    @Test
    public void testAddSize() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(10, 1);
        minHeap.add(12, 2);
        minHeap.add(31, 3);
        minHeap.add(100, 101);
        minHeap.removeSmallest();
        minHeap.removeSmallest();
        assertEquals(2, minHeap.size());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ExtrinsicMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i < 100000; i += 1) {
            heap.add(i, 100000 - i);
        }
        long end = System.currentTimeMillis();
        System.out.println("ADD: time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 10000; j += 1) {
            heap.changePriority(StdRandom.uniform(0, heap.size()), j);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("CP: time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");
    }

}
