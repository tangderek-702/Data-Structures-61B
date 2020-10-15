import java.util.*;
public class MyHashMap<K, V> implements Map61B<K, V> {
    public class Item {
        private K key;
        private V val;

        public Item(K key, V val) {
            this.key =key;
            this.val = val;
        }
    }

    private static  final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private  List<Item>[] buckets;
    private double loadFactor;
    private int size;
    private Set<K> keys;

    public  MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        buckets = (List<Item>[]) new ArrayList[initialSize];
        this.loadFactor = loadFactor;
        this.keys = new HashSet<K>();
    }

    /**Removes all of the mappings from this map*/
    public void clear() {
        buckets = (List<Item>[]) new ArrayList[DEFAULT_SIZE];
        size = 0;
        keys = new HashSet<K>();
    }

    /**Wraps the hashcode of key K modulo M. */
    private int wrap(K k, int m) {
        return Math.floorMod(k.hashCode(),m);
    }

    /** Gets the Item associated with key KEY, or null if it doesn't exist. */
    private Item getItem(K key) {
        int bucketInd = wrap(key, buckets.length);
        List<Item> bucket = buckets[bucketInd];

        if (bucket == null) {
            return null;
        }
        for (Item item : bucket) {
            if (item.key.equals(key)) {
                return item;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    public V get(K key) {
        Item item = getItem(key);

        if (item == null) {
            return null;
        } else {
            return item.val;
        }
    }

    public int size() {
        return size;
    }

    /**resize this.buckets to have length NEW SIZE*/
    private void resize(int newSize) {
        List<Item>[] newBuckets = (List<Item>[]) new ArrayList[newSize];

        for (int bucketInd = 0; bucketInd < buckets.length; bucketInd += 1){
            List<Item> bucket = this.buckets[bucketInd];
            if (bucket == null) {
                continue;
            }
            for (Item item : bucket) {
                int newBucketInd = wrap(item.key, newSize);
                if (newBuckets[newBucketInd] == null) {
                    newBuckets[newBucketInd] = new ArrayList<Item>();
                }
                List<Item> newBucket = newBuckets[newBucketInd];

                newBucket.add(item)
;            }
        }
    }

    public void put(K key,V value) {
        Item item = getItem(key);

        if (item != null) {
            item.val = value;
        } else {
            int bucketInd = wrap(key, buckets.length);
            List<Item> bucket = buckets[bucketInd];

            Item toAdd = new Item(key,value);

            if(bucket == null) {
                buckets[bucketInd] = new ArrayList<Item>();
            }
            buckets[bucketInd].add(toAdd);
            size += 1;
            keys.add(key);

            double currentLoadFactor = ((double) size) / buckets.length;

            if(currentLoadFactor > this.loadFactor){
                resize(2*buckets.length);
            }
        }
    }

    public Set<K> keySet() {
        return keys;
    }

    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

}
