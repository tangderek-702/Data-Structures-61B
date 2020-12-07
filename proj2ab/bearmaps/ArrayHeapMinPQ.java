package bearmaps;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private Node<T>[] priorityQ;
    private HashMap<T,Integer> priorityHashMap;
    private int nSize;
    private int arraySize;
    private double LOAD_FACTOR = 0.25;

    public class Node<T> {
        private double priority;
        private T item;

        public Node(double priority, T item) {
            this.priority = priority;
            this.item = item;
        }

        private void setPriority(double priority) {
            this.priority = priority;
        }

        private double getPriority() {
            return priority;
        }

    }

    public ArrayHeapMinPQ(int capacity) {
        this.priorityQ = (Node<T>[]) new Node[capacity + 1];
        this.priorityHashMap = new HashMap<>();
        this.nSize = 0;
        this.arraySize = capacity;
        priorityQ[0] = null;
    }

    public ArrayHeapMinPQ() {
        this.priorityQ = (Node<T>[]) new Node[1];
        this.priorityHashMap = new HashMap<>();
        this.nSize = 0;
        this.arraySize = 8; //arbitrary default size
        priorityQ[0] = null;
    }

    private void resizeUp() {
        Node<T>[] holder = (Node<T>[]) new Node[arraySize * 2];
        System.arraycopy(priorityQ, 0, holder, 0, arraySize);
        arraySize = arraySize * 2;
        priorityQ = holder;
    }

    private void resizeDown() {
        Node<T>[] holder = (Node<T>[]) new Node[arraySize / 2];
        System.arraycopy(priorityQ, 0, holder, 0, nSize);
        arraySize  = arraySize / 2;
        priorityQ = holder;
    }

    private int parent(int k) {
        if(k <= 1) return 1;
        return k / 2 ;
    }

    private int child(int k) {
        int test = size();
        int leftChild = k * 2 ;
        if (leftChild > size()) {
            leftChild = k;
        }
        int rightChild = k * 2 + 1;
        if (rightChild > size()) {
            rightChild = leftChild;
        }

        if (priorityQ[rightChild].getPriority() > priorityQ[leftChild].getPriority()) {
            return leftChild;
        } else {
            return rightChild;
        }
    }

    private void swap(int index1, int index2) {
        Node temp = priorityQ[index1];
        priorityQ[index1] = priorityQ[index2];
        priorityQ[index2] = temp;
    }

    private void swim(int itemIndex) {
        int test = parent(itemIndex);
        if (priorityQ[parent(itemIndex)].getPriority() > priorityQ[itemIndex].getPriority()) {
            swap(itemIndex,parent(itemIndex));
            swim(parent(itemIndex));
        }
    }

    private void sink(int itemIndex) {
        int child = child(itemIndex);
        if(priorityQ[child].getPriority() < priorityQ[itemIndex].getPriority()) {
            swap(itemIndex, child(itemIndex));
            sink(child(itemIndex));
        }
    }

    public int getArraySize() {
        return this.arraySize;
    }

    @Override
    public void add(T item, double priority) {
        if (nSize >= arraySize) {
            resizeUp();
        }
        if (priorityHashMap.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        priorityHashMap.put(item, nSize + 1);
        priorityQ[nSize + 1] = new Node<>(priority, item);
        nSize++;
        swim(nSize);
    }

    @Override
    public boolean contains(T item) {
        return priorityHashMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (priorityQ.length <= 1 ) {
            throw new NoSuchElementException();
        }
        return priorityQ[1].item;
    }

    @Override
    public T removeSmallest() {
        if (priorityQ.length <= 1) {
            throw new NoSuchElementException();
        }
        T itemHolder = priorityQ[1].item;
        priorityHashMap.remove(itemHolder);
        sink(1);
        priorityQ[1] = priorityQ[nSize + 1];
        nSize--;
        if (nSize/arraySize < LOAD_FACTOR) {
            resizeDown();
        }
        return itemHolder;
    }

    @Override
    public int size() {
        return nSize;
    }

    @Override
    public void changePriority(T item, double priority) {
        int hashIndex = priorityHashMap.get(item);
        double curPriority = priorityQ[hashIndex].priority;
        priorityQ[hashIndex].setPriority(priority);
        if (curPriority < priority) {
            sink(hashIndex);
        } else {
            swim(hashIndex);
        }
    }

}
