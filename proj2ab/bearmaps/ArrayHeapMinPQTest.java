package bearmaps;

import org.junit.Test;

import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;
import static org.junit.Assert.assertEquals;


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

        test.removeSmallest();
        assertEquals(10, test.getArraySize());
    }

}
