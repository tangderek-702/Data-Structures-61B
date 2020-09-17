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

        if (front + 1 == a.length || this.front == this.sentinel) {
            front = 1;
        } else {
            front++;
        }
        if (this.size == 0) {
            back = front;
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

        if (this.back == this.sentinel + 1 || this.back == this.sentinel) {
            back = a.length - 1;
        } else {
            back--;
        }
        if (this.size == 0) {
            front = back;
        }
        /**
        if (this.back == 0) {
            this.back = this.arraySize - 1;
        }
        */
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
        T firstElement = this.a[this.front];
        if (this.front == this.sentinel + 1 || this.front == this.sentinel) {
            this.front = this.a.length - 1;
        } else {
            this.front--;
        }
        /**
        if (this.front == 0) {
            this.front = this.arraySize - 1;
        }
         */

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
        this.size--;

        if (this.size == 0) {
            this.front = this.sentinel;
            this.back = this.sentinel;
        }

        return firstElement;
    }

    public T removeLast() {
        if (this.back == this.sentinel && this.front == this.sentinel) {
            return null;
        }
        T lastElement = this.a[this.back];
        if (this.back == this.sentinel || this.back == this.a.length - 1) {
            this.back = this.sentinel + 1;
        } else {
            back++;
        }
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
        int curIndex = this.front - index;
        int count = 0;

        if (curIndex < 0) {
            curIndex = curIndex + this.size;
        }

        if (curIndex == 0) {
            curIndex = this.arraySize - 1;
        }

        return this.a[curIndex];

        /**
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
        */
    }
}

