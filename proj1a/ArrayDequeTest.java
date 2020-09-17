public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addFirst(0);
        int rem2 = lld1.removeLast();
        lld1.addLast(2);
        lld1.addLast(3);
        int x = lld1.get(1);

        lld1.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}
