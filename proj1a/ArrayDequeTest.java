public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addLast(0);
        int smd = lld1.get(0);
        int rem1 = lld1.removeFirst();

        lld1.addFirst(3);
        int rem2 = lld1.removeFirst();
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
        int x = lld1.get(0);
        int rem3 = lld1.removeFirst();
        lld1.addLast(10);

        int rem4 = lld1.removeFirst();
        lld1.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}
