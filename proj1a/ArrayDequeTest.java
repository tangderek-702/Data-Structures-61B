public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();

        ArrayDeque.addLast(0);
        ArrayDeque.addFirst(1);
        ArrayDeque.removeLast()      ;
        ArrayDeque.removeLast()      ;
        ArrayDeque.addLast(4);
        ArrayDeque.addLast(5);
        ArrayDeque.addFirst(6);
        int x = ArrayDeque.get(2)      ;
    }

    public static void main(String[] args) {
        sanityTest();
    }
}



