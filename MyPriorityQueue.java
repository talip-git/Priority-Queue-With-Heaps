import java.util.Iterator;
//-----------------------------------------------------
// Title: Priority Queue Class
// Description: Priority Queue Implementation with heaps
//-----------------------------------------------------
public class MyPriorityQueue<Key> implements Iterable<Key> {
    private Key[] keys;
    private int size;

    public MyPriorityQueue(int initCapacity) {
        keys = (Key[]) new Object[initCapacity + 1];
        size = 0;
    }
    public MyPriorityQueue() {
        this(1);
    }

    public boolean isEmpty() {
        //--------------------------------------------------------
        // Summary: Returns the size of the queue
        //--------------------------------------------------------
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Key max() {
        //--------------------------------------------------------
        // Summary: Returns the maximum key
        // Postcondition: The Key is returned
        //--------------------------------------------------------
        if (isEmpty()){
            System.out.println("No element in the queue!");
            return null;
        }
        return keys[1];
    }
    public void add(Key item) {
        //--------------------------------------------------------
        // Summary: Adds the item to the queue
        // Precondition: item is a key
        // Postcondition: the item is added to the key
        //--------------------------------------------------------
        if (size == keys.length - 1){
            resize(2 * keys.length);
        }
        keys[++size] = item;
        swim(size);
    }
    private void resize(int capacity) {
        //--------------------------------------------------------
        // Summary: resizes the key array according to the capacity
        // Precondition: capacity is the integer that we want array to be resized
        // Postcondition: key list is resized
        //--------------------------------------------------------
        Key[] tmp = (Key[]) new Object[capacity];
        for (int i = 1; i <= size; i++) {
            tmp[i] = keys[i];
        }
        keys = tmp;
    }
    public Key delMax() {
        //--------------------------------------------------------
        // Summary: Deletes the maximum key from the list and returns it
        // Postcondition: the max item is deleted
        //--------------------------------------------------------
        if (isEmpty()){
            return null;
        }
        Key max = keys[1];

        exchange(1, size--);
        sink(1);
        keys[size + 1] = null;

        if ((size > 0) && (size == (keys.length - 1) / 4)){
            resize(keys.length / 2);
        }
        return max;
    }

    private void swim(int key) {
        //--------------------------------------------------------
        // Summary: swims the key with given integer
        // Precondition: k is the integer index
        // Postcondition: item is swimmed upwards
        //--------------------------------------------------------
        while (key > 1 && less(key / 2, key)) {
            exchange(key, key / 2);
            key = key / 2;
        }
    }

    private void sink(int key) {
        //--------------------------------------------------------
        // Summary: sinks the key with given integer
        // Precondition: k is the integer index
        // Postcondition: item is sink downwards
        //--------------------------------------------------------
        while (2 * key <= size) {
            int i = 2 * key;
            if (i < size && less(i, i + 1)){
                i++;
            }
            if (!less(key, i)){
                break;
            }
            exchange(key, i);
            key = i;
        }
    }

    private boolean less(int i, int j) {
        //--------------------------------------------------------
        // Summary: compares the item
        //--------------------------------------------------------
        return ((Comparable<Key>) keys[i]).compareTo(keys[j]) < 0;
    }

    private void exchange(int i, int j) {
        //--------------------------------------------------------
        // Summary: Exchanges the keys with given integers
        //--------------------------------------------------------
        Key swap = keys[i];
        keys[i] = keys[j];
        keys[j] = swap;
    }

    public Iterator<Key> iterator() {
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Key> {
        private MyPriorityQueue<Key> tmp =  new MyPriorityQueue<>();
        public QueueIterator() {
            for (int i = 1; i <= size; i++)
                tmp.add(keys[i]);
        }

        public boolean hasNext() {
            return !tmp.isEmpty();
        }

        public Key next() {
            if (!hasNext()) {
               return null;
            }
            return tmp.delMax();
        }
    }
}
