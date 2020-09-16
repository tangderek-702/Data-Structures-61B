public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(9);
        lld1.addFirst(8);
        lld1.addFirst(7);
        lld1.addFirst(6);
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);

        lld1.addLast(10);
        lld1.addLast(11);
        lld1.addLast(12);
        lld1.addFirst(0);
        lld1.removeFirst();
        lld1.removeLast();
        lld1.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}
