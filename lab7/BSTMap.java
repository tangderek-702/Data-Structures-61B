import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private  Node root;
    private int size = 0;
    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    private Node get(Node x, K key) {
        if (x == null) {
            return null;
        } else if (x.key.equals(key)) {
            return x;
        } else if (x.key.compareTo(key) < 0){
            return get(x.right, key);
        } else {
            return get(x.left, key);
        }
    }

    @Override
    public V get(K key) {
        Node result = get(root, key);
        if(result ==null) {
            return null;
        } else {
            return result.val;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private Node putHelp(K key, V value, Node n) {
        if (n == null) {
            return new Node(key, value);
        } else if (n.key.equals(key)) {
            n.val = value;
        } else if (n.key.compareTo(key) < 0) {
            n.right = putHelp(key, value, n.right);
        } else {
            n.left = putHelp(key, value, n.left);
        }
        return n;
    }
    @Override
    public void put(K key, V value) {
        root = putHelp(key,value,root);

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException;
    }
}
