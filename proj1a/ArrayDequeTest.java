public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addLast(0);
        lld1.addLast(1);
        lld1.removeFirst();
        lld1.addLast(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        int x = lld1.get(1);
        lld1.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}
