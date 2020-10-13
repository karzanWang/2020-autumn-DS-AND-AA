package lab_three;

class HeapOverflowException extends Exception {
    public HeapOverflowException(String message) {
        super(message);
    }
}

class HeapEmptyException extends Exception {
    public HeapEmptyException(String message) {
        super(message);
    }
}

/**
 * You may use {@link java.util.Queue}.
 */
public class MyHeap {
    private final int capacity;
    private int[] items;
    private static final int MAX_CAPACITY = 10_000_001;
    /**
     * {@code ptr} is to keep the index of last item.
     * It will start from 1.
     */
    private int size = 0;

    /**
     * init
     *
     * @param capacity capacity
     */
    public MyHeap(int capacity) {
        // 1 <= capacity <= MAX_CAPACITY
        // because that the index 0 will not be used
        this.capacity = Math.min(Math.max(capacity + 1, 2), MyHeap.MAX_CAPACITY);
        this.items = new int[this.capacity];
    }

    /**
     * capacity of the heap
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity - 1;
    }

    /**
     * insert an item into the heap
     *
     * @param x item
     * @throws HeapOverflowException too many items
     */
    public void insert(int x) throws HeapOverflowException {
        this.size++;
        if (this.size < this.capacity) {
            this.items[this.size] = x;
        } else
            throw new HeapOverflowException("Too many items");
        int idx = this.size;
        int parent = idx / 2;
        while (idx > 1 && this.items[parent] > this.items[idx]) {
            this.items[idx] = this.items[parent];
            this.items[parent] = x;
            idx = parent;
            parent = idx / 2;
        }
    }

    /**
     * find min value
     *
     * @return min value
     * @throws HeapEmptyException heap may be empty
     */
    public int findMin() throws HeapEmptyException {
        if (this.size > 0) {
            return this.items[1];
        } else throw new HeapEmptyException("No items");
    }

    /**
     * find min value, and remove it
     *
     * @return deleted min value
     * @throws HeapEmptyException heap may be empty
     */
    public int deleteMin() throws HeapEmptyException {
        if (this.size < 1) {
            throw new HeapEmptyException("No items");
        }
        int value = this.items[1];
        this.items[1] = this.items[this.size];
        this.size--;
        fixDown();
        return value;
    }

    private void fixDown() {
        int f = 1;
        int k;
        while ((k = f * 2) <= this.size) {
            if (k <= this.size - 1) {
                if (this.items[k] > this.items[k + 1]) k++;
            }
            if (this.items[f] <= this.items[k]) break;

            int tmp = this.items[f];
            this.items[f] = this.items[k];
            this.items[k] = tmp;
            f = k;
        }
    }

    /**
     * isEmpty
     *
     * @return whether the heap is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * remove all items in the heap
     */
    public void makeEmpty() {
        this.items = new int[capacity];
        this.size = 0;
    }


}
