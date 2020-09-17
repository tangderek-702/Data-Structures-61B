public class LinkedListDeque<T> {
    /** TNode Class */
    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;
        /** TNode Constructor */
        TNode(TNode last, T label, TNode nxt) {
            prev = last;
            item = label;
            next = nxt;
        }
    }

    private int size = 0;
    private TNode sentinel;
    // class variable

    /** LLDeque Constructor: creates empty LL */
    public LinkedListDeque() {
        this.sentinel = new TNode(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        this.sentinel.next = new TNode(this.sentinel, item, this.sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        this.sentinel.prev = new TNode(this.sentinel.prev, item, this.sentinel);
        this.sentinel.prev.prev.next = this.sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode ptr = this.sentinel.next;
        while (ptr.next != null && ptr != this.sentinel) {
            System.out.println(ptr.item);
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        TNode item = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        item.next = null;
        item.prev = null;
        this.size--;
        return item.item;
    }

    public T removeLast() {
        TNode item = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        item.prev = null;
        item.next = null;
        this.size--;
        return item.item;
    }

    public T get(int index) {
        int currentIndex = 0;
        TNode copy = sentinel;
        while (copy.next != null) {
            if (index + 1 == currentIndex) {
                return copy.item;
            }
            copy = copy.next;
            currentIndex++;
        }
        return null;
    }

    public T getRecursive(int index) {
        return get(index);
    }
}
