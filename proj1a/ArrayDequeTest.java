public class ArrayDequeTest {
    public static void sanityTest() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();

        a.addFirst(0);
        a.addLast(1);
        int y = a.removeLast();
        a.addLast(3);

        int x = a.removeFirst();
        int z = a.removeFirst();

        a.addFirst(6);
        int rem = a.removeLast();
        a.addFirst(8);
        a.addLast(9);
        int hi = a.get(1);

        int rem1 = a.removeLast();
        int rem2 = a.removeLast();

        a.addLast(13);
        a.addLast(14);
        a.addFirst(15);
        a.addLast(16);


        int b = a.get(1);
        a.addLast(18);
        a.addFirst(19);
        a.addFirst(20);
        int rem3 = a.removeLast();
        int d = a.get(4);

        a.printDeque();
    }

    public static void main(String[] args) {
        sanityTest();
    }
}



