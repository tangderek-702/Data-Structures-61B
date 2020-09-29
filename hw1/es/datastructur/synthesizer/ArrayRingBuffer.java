package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int capacity;
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.fillCount = 0;
        this.last = 0;
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }



    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        rb[last] = x;
        last++;
        fillCount++;
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.

        T removed =  rb[first];
        rb[first] = null;
        fillCount--;
        first++;
        if (first == capacity) {
            first = 0;
        }
        return removed;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        return rb[this.first];
    }



    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ArrayIter implements Iterator<T> {
        private int iterPos;
        public ArrayIter() {
            iterPos = 0;
        }
        public boolean hasNext() {
            return iterPos < fillCount;
        }
        public T next() {
            T item = rb[iterPos];
            iterPos++;
            return item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayIter();
    }
}
    // TODO: Remove all comments that say TODO when you're done.
