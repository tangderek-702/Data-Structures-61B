public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();

        ArrayDeque.addFirst(0);
        ArrayDeque.get(0)      ;
        ArrayDeque.removeFirst()     ;
        ArrayDeque.addFirst(3);
        ArrayDeque.addFirst(4);
        ArrayDeque.get(0)      ;
        ArrayDeque.get(1)      ;
        ArrayDeque.addFirst(7);
        ArrayDeque.removeFirst()     ;
        ArrayDeque.addLast(9);
        ArrayDeque.addLast(10);
        ArrayDeque.addLast(11);
        ArrayDeque.removeLast()      ;
        ArrayDeque.addFirst(13)
        ArrayDeque.get(0)      ==> 13
        ArrayDeque.addLast(15)
        ArrayDeque.get(2)      ==> 3
        ArrayDeque.addLast(17)
        ArrayDeque.addFirst(18)
        ArrayDeque.removeFirst()     ==> 18
        ArrayDeque.removeFirst()     ==> 13
        ArrayDeque.addFirst(21)
        ArrayDeque.removeFirst()     ==> 21
        ArrayDeque.removeLast()      ==> 15
    }

    public static void main(String[] args) {
        sanityTest();
    }
}



