public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addFirst(0);
        lld1.addLast(1);
        int rem2 = lld1.removeFirst();
        lld1.addLast(3);
        lld1.addLast(4);
        int rem = lld1.removeFirst();
        lld1.addLast(6);
        int x = lld1.get(2);

        lld1.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}
