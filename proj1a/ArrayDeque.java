import java.util.Arrays;

public class ArrayDeque<T> {

    private int front;
    private int back;
    private int sentinel;
    private T[] a;
    private int arraySize = 8;
    private int size = 0;


    public ArrayDeque() {
        this.front = 0;
        this.back = 0;
        this.sentinel = 0;
        this.a = (T[]) new Object[arraySize];
        this.size = 0;
    }

    public void addFirst(T item) {
        // size up when full
        if (this.size + 1 == this.a.length) {
            // perform resizing operation
            T[] aHolder = Arrays.copyOf(this.a, arraySize * 2);
            int numCopyElements = this.arraySize - back - 1;
            this.arraySize *= 2;
            if (back != sentinel) {
                System.arraycopy(this.a, back, aHolder,
                        arraySize - 1 - (numCopyElements), numCopyElements);
                back = arraySize - numCopyElements;
            }
            this.a = aHolder;
        }
        if (front + 1 == a.length) {
            front = 0;
        } else {
            front++;
        }
        a[front] = item;
        size++;
    }
    public void addLast(T item) {
        if (this.size + 1 == this.a.length) {
            // perform resizing operation
            T[] aHolder = Arrays.copyOf(this.a, arraySize * 2);
            int numCopyElements = this.arraySize - back - 1;
            this.arraySize *= 2;
            if (back != sentinel) {
                System.arraycopy(this.a, back, aHolder,
                        arraySize - 1 - (numCopyElements), numCopyElements);
                back = arraySize - numCopyElements;
            }
            this.a = aHolder;
        }
        if (back - 1 == -1) {
            back = a.length - 1;
        } else {
            back--;
        }
        a[back] = item;
        size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            this.get(i);
            System.out.println(this.get(i));
        }
    }
    public T removeFirst() {
        if (this.back == this.sentinel && this.front == this.sentinel) {
            return null;
        }
        if (this.front == this.sentinel) {
            this.front = this.a.length - 1;
        }
        T firstElement = this.a[this.front];
        this.front--;

        if (this.size == 0) {
            this.front = this.sentinel;
            this.back = this.sentinel;
        }

        double ratio  = (double) this.size / (double) this.arraySize;
        if (ratio <= 0.25 && this.arraySize > 8) {
            T[] aHolder = Arrays.copyOf(this.a, arraySize / 2);
            int numCopyElements = this.arraySize - back - 1;
            this.arraySize /= 2;
            if (back != sentinel) {
                System.arraycopy(this.a, back, aHolder,
                        arraySize - 1 - (numCopyElements), numCopyElements);
                back = arraySize - numCopyElements;
            }
            this.a = aHolder;

        }
        size--;

        return firstElement;
    }

    public T removeLast() {
        if (this.back == this.sentinel && this.front == this.sentinel) {
            return null;
        }
        if (this.back == this.sentinel || this.back == this.a.length) {
            this.back = this.sentinel + 1;
        }
        T lastElement = this.a[this.back];
        back++;
        this.size--;

        if (this.size == 0) {
            this.front = this.sentinel;
            this.back = this.sentinel;
        }
        double ratio  = (double) this.size / (double) this.arraySize;
        if (ratio <= 0.25 && this.arraySize > 8) {
            T[] aHolder = Arrays.copyOf(this.a, arraySize / 2);
            int numCopyElements = this.arraySize - back - 1;
            this.arraySize /= 2;
            if (back != sentinel) {
            System.arraycopy(this.a, back, aHolder,
                 arraySize - 1 - (numCopyElements), numCopyElements);
            back = arraySize - numCopyElements;
            }
            this.a = aHolder;
        }
         return lastElement;
    }
    public T get(int index) {
        if (this.size <= index) {
            return null;
        }
        int curIndex = this.front;
        int count = 0;
        int frontElements = this.front;
            if (index + 1 > frontElements) {
                index = index - frontElements;
                curIndex = this.sentinel;
            } else {
                return this.a[this.front - index];
            }
        if (curIndex == this.sentinel) {
            curIndex = this.a.length - 1;
        }
        return this.a[curIndex - index];

    }

}
