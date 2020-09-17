public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();

        a.addFirst(0);
        int smd = a.get(0);
        int x = a.removeFirst();
        a.addFirst(3);
        int y = a.removeLast();
        a.addLast(5);
        a.addFirst(6);
        a.addFirst(7);
        a.addLast(8);
        a.addLast(9);
        int rem = a.removeLast();
        int rem1 = a.removeLast();
        int rem2 = a.removeLast();
        int rem3 = a.removeLast();
        int b = a.get(0);
        int c = a.get(0);
        a.addLast(16);
        a.addLast(17);
        a.addFirst(18);
        int d = a.get(3);

        a.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}



